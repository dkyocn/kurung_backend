package com.kurung.common.config;

import com.kurung.common.security.filter.JWTFilter;
import com.kurung.common.security.filter.LoginFilter;
import com.kurung.common.security.handler.CustomLogoutHandler;
import com.kurung.common.util.JWTUtil;
import com.kurung.common.security.service.CustomUserDetailsService;
import com.kurung.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    // 로그아웃 핸들러 Bean 등록
    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler(jwtUtil, userService);
    }

    // 인증 관리자 (AuthenticationManager) 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 🌐 CORS 정책 설정 (React 연동 시 필요)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 프론트 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("token-expired", "Authorization", "RefreshToken")
                .allowCredentials(true);
    }

    // 🔒 보안 필터 체인 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스 및 공개 URL 허용
                        .requestMatchers("/{spring:[a-zA-Z0-9-_]+}", "/{spring:[a-zA-Z0-9-_]+}/**").permitAll()
                        .requestMatchers("/.well-known/**", "/error", "/photo/**").permitAll()
                        .requestMatchers("/", "/index.html", "/favicon.ico", "/static/**", "/manifest.json",
                                "/public/**", "/auth/**", "/css/**", "/js/**", "/*.png").permitAll()

                        // 인증 없이 허용되는 API 경로
                        .requestMatchers("api/v1/kurung/login", "api/v1/kurung/reissue", "/user/signup", "/user/idchk",
                                "/notice/ntop3", "/notice", "/notice/nfdown", "/notice/search/title",
                                "/notice/detail/*", "/board/btop3", "/board/search/**").permitAll()

                        // 인증 필요
                        .requestMatchers("/logout").authenticated()

                        // 관리자 권한 필요
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 사용자/관리자 권한 모두 허용
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/update/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/board/detail/{boardNum}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/board").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/board/{boardNum}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/board/{boardNum}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reply/insert").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/reply/update/{replyNum}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/reply/delete/{replyNum}").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )

                // JWT 인증 필터 먼저 실행
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

                // 예외 처리 설정
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"error\": \"접근이 거부되었습니다.\"}");
                        })
                )

                // LoginFilter 추가 (JWT 발급 로직 포함)
                .addFilterAt(new LoginFilter(authenticationManager, jwtUtil, userService),
                        UsernamePasswordAuthenticationFilter.class)

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("api/v1/kurung/user/logout")
                        .addLogoutHandler(customLogoutHandler())
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("로그아웃 성공");
                        })
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
