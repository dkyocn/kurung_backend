package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //회원가입시 아이디 체크
    @Override
    @Transactional
    public boolean registerUser(UserEntity user) {
        if (validateUser(user)) {
            return false;
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getUserPwd());
        log.info("회원가입 - 사용자 ID: {}, 비밀번호 암호화 완료", user.getUserId());

        user.assignUserUuid(generateUuid()); // 또는 Builder 내부에서 uuid 설정되도록
        userRepository.save(user);
        return true;
    }

    //아이디가 db에 있는지 확인 메소드!
    @Override
    public boolean checkUserIdAvailability(String userId) {
        return !userRepository.existsByUserId(userId);
    }
    //중복 유저 검사 메소드!
    public boolean validateUser(UserEntity user) {
        return userRepository.existsByUserId(user.getUserId());
    }

    //신규 uuid 생성하는 메소드!
    public String generateUuid() {
        // 1. 오늘 날짜 yyyyMMdd 형식으로
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 2. 오늘 가입된 사용자 수 조회 (예: userId가 오늘 날짜로 시작하는 사용자 수)
        long count = userRepository.countByUuidStartingWith(today);
        // 3. 순번은 1부터 시작 → count + 1
        String sequence = String.format("%02d", count + 1);  // 두 자리로 포맷 (예: 01, 02, 10)
        // 4. 최종 UUID 반환
        return today + sequence;  // 예: 2025072302
    }

    // 로그인 체크 메소드!
    public UserEntity loginCheck(UserEntity user) {
        Optional<UserEntity> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()) {
            UserEntity findUser = optionalUser.get();
            // BCrypt로 암호화된 비밀번호와 비교
            if (passwordEncoder.matches(user.getUserPwd(), findUser.getUserPwd())) {
                log.info("로그인 성공 - 사용자 ID: {}", user.getUserId());
                return findUser;
            } else {
                log.warn("로그인 실패 - 비밀번호 불일치 - 사용자 ID: {}", user.getUserId());
            }
        } else {
            log.warn("로그인 실패 - 존재하지 않는 사용자 ID: {}", user.getUserId());
        }
        return null;
    }

    //User uuid로 사용자를 조회해서 DTO로 반환하는 메소드!
    @Override
    public UserDTO getUserByUuid(String userUuid) {
        UserEntity userEntity = userRepository.getUserByUuid(userUuid);
        if (userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        return UserDTO.toUserBuilder()
            .userEntity(userEntity)
            .build();
    }

    //User id로 사용자를 조회해서 DTO로 반환하는 메소드!
    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.getByUserId(userId);
        if (userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        return UserDTO.toUserBuilder()
            .userEntity(userEntity)
            .build();
    }

    //RefreshToken 업데이트 메소드!
    @Override
    @Transactional
    public void updateRefresh(UserDTO userDTO, String refreshToken) {
        UserEntity userEntity = userRepository.getUserByUuid(userDTO.getUserUuid());
        if (userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        if ((userEntity.getUserRefreshToken() == null) || (!userEntity.getUserRefreshToken()
            .equals(refreshToken))) {
            userEntity.updateRefresh(refreshToken);
        }
    }
}




