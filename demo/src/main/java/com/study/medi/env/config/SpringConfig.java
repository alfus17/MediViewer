package com.study.medi.env.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
	@Value("${dicome.img.path}") // 변수 파일에 등록된 java.file.test 값 가져오기
	String envImgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("envImgPath"+envImgPath);
	 registry.addResourceHandler("/dicome/img/**")
         .addResourceLocations("file:///" + envImgPath + "/");
    }
}