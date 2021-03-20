package com.ablog.config;

import com.ablog.filter.RewriteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean filterRegistration(){

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RewriteFilter());//注册rewrite过滤器
        registration.addUrlPatterns("/*");
        registration.addInitParameter(RewriteFilter.REWRITE_TO, "/static/index.html");
        registration.addInitParameter(RewriteFilter.REWRITE_PATTERNS, "/ui/*");
        registration.setName("rewriteFilter");
        registration.setOrder(1);
        return registration;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
