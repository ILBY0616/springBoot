package studentManage.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径的跨域请求
                .allowedOriginPatterns("*") // 允许所有域名的请求
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                .allowCredentials(true) // 允许携带凭证（如 cookies）
                .maxAge(3600) // 预检请求的有效期，单位为秒
                .allowedHeaders("*"); // 允许的请求头
    }
}
