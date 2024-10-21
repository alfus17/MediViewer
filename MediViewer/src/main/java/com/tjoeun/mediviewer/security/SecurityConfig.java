package com.tjoeun.mediviewer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // csrf 설정을 끈다 (현재 단계에서는 필요하지 않기 때문)
            .authorizeRequests() // 요청에 대한 인가를 설정한다 -> 각 요청이 어떤 권한을 필요로 하는지 정의
            .requestMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll() // 경로에는 누구나 접근 가능하게 함
            .anyRequest().authenticated() // 인증 필요 여부를 확인, 위에서 명시적으로 허용한 경로들을 제외하고는 전부 인증을 필요로 하게 함
            .and() // 위의 설정을 마친 후 새로운 설정을 이어나가게 하는 연결자
            .formLogin() // 25, 26 줄 : 시큐리티가 기본적으로 제공하는, 보호된 경로에 접근하려고 할 때 인증되어있지 않으면 /login 으로 가게 함
            .loginPage("/login") // 여기 주소를 /asd로 하면 거기로 감
            .permitAll() // 로그인 페이지에 접근하는 건 누구나 가능하게 함
            .and()
            .logout() // 로그아웃 기능 활성화
            .permitAll(); // 로그아웃 페이지에 접근하는 건 누구나 가능하게 함
        
        return http.build();
    }
    
	// 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder(); 
    }

    // 사용자 인증을 위한 부분
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception 
    {
        return authenticationConfiguration.getAuthenticationManager();
        // authenticationConfiguration : 스프링 시큐리티의 인증 설정을 포함하는 클래스. 스프링 시큐리티의 설정 및 구성 정보를 제공. 스프링이 자동으로 제공
    }
}