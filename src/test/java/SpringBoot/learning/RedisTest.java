package SpringBoot.learning;

import net.sf.jsqlparser.statement.select.KSQLJoinWindow;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import java.util.concurrent.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        //往redis中存储一个键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        operations.set("username","zhangsan");
        operations.set("id","1", 15, TimeUnit.SECONDS);

        //测试代码获取结果
        System.out.println(operations.get("username"));
    }
}
