package com.kurung.common.security.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=== CustomUserDetailsService.loadUserByUsername 시작 ===");
        log.info("요청된 username: {}", username);
        
        // 1. DB에서 사용자 조회
        UserDTO userDTO = userService.getUserByUserId(username);

        if (userDTO == null) {
            log.warn("사용자를 찾을 수 없습니다: {}", username);
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        // 2. 비밀번호, 권한 등 디버깅 로그 출력
        log.info("DB에서 조회된 사용자: {}", userDTO);
        log.info("DB 비밀번호: {}", userDTO.getUserPwd());
        log.info("BCrypt 여부: {}", userDTO.getUserPwd() != null && userDTO.getUserPwd().startsWith("$2a$") ? "YES" : "NO");
        log.info("관리자 여부 (adminYn): {}", userDTO.isAdminYN());

        // 3. ROLE 부여 (int → String 변환)
        String role = (userDTO.isAdminYN()) ? "ADMIN" : "USER";
        log.info("부여된 ROLE: {}", role);

        log.info("=== CustomUserDetailsService.loadUserByUsername 완료 ===");
        
        return User.builder()
            .username(userDTO.getUserId())
            .password(userDTO.getUserPwd())
            .roles(role)
            .build();
    }
}
