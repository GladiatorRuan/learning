package SpringBoot.learning.controller;

import SpringBoot.learning.pojo.Result;
import SpringBoot.learning.pojo.User;
import SpringBoot.learning.service.UserService;
import SpringBoot.learning.utils.JwtUtil;
import SpringBoot.learning.utils.Md5Util;
import SpringBoot.learning.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //查询用户，如果占用只能换，否则可以注册
        User u = userService.findByUserName(username);
        if (u == null){
            userService.register(username, password);
            return Result.success();
        }
        else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{4,16}$") String password){
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);

        //判断用户是否存在
        if (loginUser == null)
            return Result.error("用户名错误");
        //判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            //把Token存到redis里面
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

            //把Token存到redis中并设置过期时间
            operations.set(token, token,1, TimeUnit.HOURS);
            return Result.success(token);
        }

        return Result.error("密码错误");

    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String , String> params,@RequestHeader("Authorization") String token){
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("缺少必要的参数");
        }
        //2.调用service完成密码更新
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        if (!re_pwd.equals(new_pwd)){
            return Result.error("两次密码填写不正确");
        }
        userService.updatePwd(new_pwd);
        //密码更新之后需要删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
