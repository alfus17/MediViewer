package com.tjoeun.mediviewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tjoeun.mediviewer.service.CustomMemberDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomMemberDetailsService memberDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // csrf 설정을 끈다 (현재 단계에서는 필요하지 않기 때문)
            .authorizeRequests() // 요청에 대한 인가를 설정한다 -> 각 요청이 어떤 권한을 필요로 하는지 정의
            .requestMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll() // 경로에는 누구나 접근 가능하게 함
            .anyRequest().authenticated() // 인증 필요 여부를 확인, 위에서 명시적으로 허용한 경로들을 제외하고는 전부 인증을 필요로 하게 함
            .and() // 위의 설정을 마친 후 새로운 설정을 이어나가게 하는 연결자
            .formLogin() // 30, 31 줄 : 시큐리티가 기본적으로 제공하는, 보호된 경로에 접근하려고 할 때 인증되어있지 않으면 /login 으로 가게 함
            .loginPage("/login") // 여기 주소를 /asd로 하면 거기로 감
            .permitAll() // 로그인 페이지에 접근하는 건 누구나 가능하게 함
            .and()
            .logout() // 로그아웃 기능 활성화
            .permitAll(); // 로그아웃 페이지에 접근하는 건 누구나 가능하게 함
        
        return http.build();
    }

    // CustomMemberDetailsService를 호출하는 부분
    // 사용자 인증을 위한 부분
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    	// AuthenticationManager : 사용자 인증을 처리하는 역할
    	// authManager(HttpSecurity http) : HttpSecurity 객체를 인자로 받아서 HTTP요청에 대한 보안 설정을 다루는 역할
    	// throws Exception : 이 메소드가 실행되는 동안 발생할 수 있는 예외를 처리하기 위함
    	
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // 앞부분 (AuthenticationManagerBuilder) : 사용자 인증을 구성하는 데 사용됨. (사용자 인증에 대한 세부 설정을 만들기 위한 빌더)
        // 뒷부분 (http.get ~ class)) : HttpSecurity 객체에서 AuthenticationManagerBuilder를 가져옴
        
        authenticationManagerBuilder.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder());
        // 52 줄 전체가 인증을 어디에서 처리할 지 정하는 줄 (여기서는 memberDetailsService로 정함)
        // userDetailsService는 시큐리티에서 제공하는 서비스
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위해 BCryptPasswordEncoder 사용
    }
}
/*
	SecurityFilterChain이 사실 가로채는 역할 (모든 요청을 가로챔)
	원래는 LoginController의 "/"로 가야 하는데 로그인이 안 되어있으니깐 /login으로 이동시켜줌
	그 후 로그인 버튼을 누르면 CustomMemberDetailsService에서 loadUserByUsername 메소드가 사용자 정보를 가져옴
	데이터베이스에서 해당 사용자의 정보를 조회 후 로그인
	로그인을 하면 LoginController에 있는 "/" 페이지로 이동
*/