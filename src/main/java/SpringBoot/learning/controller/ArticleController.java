package SpringBoot.learning.controller;

import SpringBoot.learning.pojo.Result;
import SpringBoot.learning.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response) {
//
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//        } catch (Exception e) {
//            //若失败，则http响应状态码是401
//            response.setStatus(405);
//            return Result.error("未登陆...");
//        }
        return Result.success("所有文章...");
    }
}
