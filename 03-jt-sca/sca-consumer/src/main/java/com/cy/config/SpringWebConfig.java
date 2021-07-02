package com.cy.config;

import com.cy.interceptor.TimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 47HLJ
 * @date 2021/7/2 11:38
 */

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TimeInterceptor()).addPathPatterns("/consumer/*");
    }

}
