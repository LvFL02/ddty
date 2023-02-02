package com.lvfl.springboot.config;

import com.lvfl.springboot.config.Interceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Interceptor())
                .addPathPatterns()
    .excludePathPatterns("/user/login","/user/register","/user/","/novel/","/novel/page","/novelchapter/","/user/export","/user/import"); //拦截所有访问，验证token 但需要放开登录、注册
    }
    @Bean
    public Interceptor Interceptor() {
        return new Interceptor();
    }
}
