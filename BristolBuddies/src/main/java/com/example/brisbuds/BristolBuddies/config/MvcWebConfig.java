//package com.example.brisbuds.BristolBuddies.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.CacheControl;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@EnableWebMvc
//public class MvcWebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/html/", ".html");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/", "D:/statics/")
//                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//    }
//}