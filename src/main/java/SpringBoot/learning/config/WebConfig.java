package SpringBoot.learning.config;

import SpringBoot.learning.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor LoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆界面不拦截
        registry.addInterceptor(LoginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}
