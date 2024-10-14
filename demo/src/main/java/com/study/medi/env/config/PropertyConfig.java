package com.study.medi.env.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

// 환경변수 사용 config 파일
@Configuration
@PropertySources(
		@PropertySource("classpath:properties/env.properties")
)
public class PropertyConfig {

}
