package com.kurung.common.security;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userUuid) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserUuid(userUuid);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userUuid);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())
                .password(user.getUserPwd())
                .roles(user.isAdminYN() ? "ADMIN" : "USER")
                .build();
    }
}
