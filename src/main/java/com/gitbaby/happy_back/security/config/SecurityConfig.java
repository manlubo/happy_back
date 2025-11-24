package com.gitbaby.happy_back.security.config;

import com.gitbaby.happy_back.domain.member.en.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  @Value("${custom.frontend-url}")
  private String frontendUrl;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(Customizer.withDefaults())
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/**").permitAll()
        .requestMatchers("/api/org/**").hasRole(Role.ORG.toString())
        .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.toString())
        .anyRequest().authenticated()
      )
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    // CORS 정책 객체 생성
    CorsConfiguration configuration = new CorsConfiguration();

    // 주소 설정
    configuration.setAllowedOrigins(List.of(frontendUrl));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

    // 쿠키, Authorization 같은 자격 증명 정보를 포함한 요청 허용
    configuration.setAllowCredentials(true);

    // 브라우저가 요청할 때 추가하는 모든 커스텀 헤더를 허용 ("Authorization", "Content-Type" 이런식으로 사용하기도 함)
    configuration.setAllowedHeaders(List.of("*"));

    // CORS 매핑 소스 생성 (URL 패턴별로 CORS 정책을 등록하는 역할)
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    // 모든 경로("/**")에 대해 위에서 정의한 configuration 적용
    source.registerCorsConfiguration("/**", configuration);

    // Spring Security filter chain에서 참조할 수 있도록 Bean 반환
    return source;
  }
}
