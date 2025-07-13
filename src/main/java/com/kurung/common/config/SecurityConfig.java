package com.kurung.common.config;

import com.kurung.common.filter.JWTFilter;
import com.kurung.common.filter.LoginFilter;
import com.kurung.common.handler.CustomLogoutHandler;
import com.kurung.common.security.CustomUserDetailsService;
import com.kurung.common.util.JWTUtil;
import com.kurung.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.kurung.user.service.RefreshService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public SecurityConfig(JWTUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("token-expired", "Authorization", "RefreshToken")
                .allowCredentials(true);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authenticationManager,
                                                   CustomLogoutHandler customLogoutHandler) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/{spring:[a-zA-Z0-9-_]+}", "/{spring:[a-zA-Z0-9-_]+}/**").permitAll()
                        .requestMatchers("/.well-known/**", "/error").permitAll()
                        .requestMatchers("/photo/**").permitAll()
                        .requestMatchers("/", "/index.html", "/favicon.ico", "/static/**", "/manifest.json",
                                "/public/**", "/auth/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/*.png").permitAll()
                        .requestMatchers("/login", "/reissue", "/user/signup", "/user/idchk",
                                "/notice/ntop3", "/notice", "/notice/nfdown", "/notice/search/title",
                                "/notice/detail/*", "/board/btop3", "/board/search/**").permitAll()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
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
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("{\"error\": \"접근이 거부되었습니다.\"}");
                        })
                )
                .addFilterAt(new LoginFilter(authenticationManager, jwtUtil, userRepository),
                        UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler)
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
