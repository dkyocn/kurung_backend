package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kurung.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 확인용

    // UUID로 사용자 정보 조회
    @Override
    public UserDTO getUserByUuid(String userUuid) {
        UserEntity userEntity = userRepositorySupport.findByUserUuid(userUuid);
        if (userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        return UserDTO.toUserBuilder()
                .userEntity(userEntity)
                .build();
    }

    // 로그인 로직 구현 - UserEntity 직접 반환
    @Override
    public UserEntity login(UserEntity userEntity) {
        String userUuid = userEntity.getUserUuid(); // UUID 꺼냄
        String rawPassword = userEntity.getUserPwd();

        // UUID로 사용자 조회
        UserEntity user = userRepository.findByUserUuid(userUuid); // 오류 해결
        if (user == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        // 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, user.getUserPwd())) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.PASSWORD_MISMATCH);
        }
        return user;
    }
}
