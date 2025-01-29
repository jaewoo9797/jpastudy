package com.jaewoo.blogdemo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // CSS 파일 처리
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        // JavaScript 파일 처리
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        // 이미지 파일 처리 (예: /images/logo.png 요청 처리)
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        // 정적 파일 캐싱
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600);  // 1시간 = 60 * 60
    }

}
