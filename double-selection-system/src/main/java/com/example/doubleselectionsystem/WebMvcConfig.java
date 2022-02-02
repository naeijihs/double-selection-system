package com.example.doubleselectionsystem;

import com.example.doubleselectionsystem.interceptor.LoginInterceptor;
import com.example.doubleselectionsystem.interceptor.StudentInterceptor;
import com.example.doubleselectionsystem.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
        registry.addInterceptor(studentInterceptor)
                .addPathPatterns("/student/**");
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/teacher/**");
    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(configSource);
    }
}
