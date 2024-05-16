package spongeBob.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/homePage").setViewName("homePage");
        registry.addViewController("/roleIntroduction").setViewName("roleIntroduction");
        registry.addViewController("/representativeWork").setViewName("representativeWork");
        registry.addViewController("/contactWithMe").setViewName("contactWithMe");
        registry.addViewController("/userCenter").setViewName("userCenter");
    }
}

