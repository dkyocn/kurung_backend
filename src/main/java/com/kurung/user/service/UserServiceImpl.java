package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.UserSignupResponseDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.social.dto.SocialUserInfo;
import com.kurung.user.social.dto.SocialLoginResponseDTO;
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
    
    // 소셜 로그인 관련 메서드 구현
    @Override
    public UserDTO socialLogin(String socialToken, UserPath userPath) {
        // TODO: 소셜 토큰 검증 및 사용자 정보 조회 로직 구현
        // 현재는 기본 구조만 제공
        log.info("소셜 로그인 시도 - 경로: {}", userPath);
        return null;
    }
    
    @Override
    @Transactional
    public boolean socialSignup(SocialUserInfo socialUserInfo) {
        // 소셜 회원가입 로직
        UserEntity user = UserEntity.builder()
            .userKey(socialUserInfo.getSocialUserId())
            .userPath(socialUserInfo.getUserPath())
            .userId(socialUserInfo.getEmail() != null ? socialUserInfo.getEmail() : 
                   socialUserInfo.getUserPath().name() + "_" + socialUserInfo.getSocialUserId())
            .userNick(socialUserInfo.getNickname())
            .userGender(socialUserInfo.getGender() != null ? socialUserInfo.getGender() : Gender.MALE)
            .profileImg(socialUserInfo.getProfileImage())
            .userPwd("SOCIAL_USER") // 소셜 사용자는 비밀번호 사용하지 않음
            .isActive(true)
            .adminYN(false)
            .userFaceLoginYN(false)
            .build();
            
        user.assignUserUuid(generateUuid());
        userRepository.save(user);
        
        log.info("소셜 회원가입 완료 - 사용자 ID: {}, 경로: {}", user.getUserId(), socialUserInfo.getUserPath());
        return true;
    }
    
    @Override
    public UserDTO getUserBySocialInfo(UserPath userPath, String userKey) {
        UserEntity userEntity = userRepository.findByUserPathAndUserKey(userPath, userKey);
        if (userEntity == null) {
            return null;
        }
        return UserDTO.toUserBuilder()
            .userEntity(userEntity)
            .build();
    }

    @Override
    public SocialLoginResponseDTO testSocialLogin(UserPath userPath, String socialUserId) {
        UserDTO existingUser = getUserBySocialInfo(userPath, socialUserId);
        if (existingUser != null) {
            return SocialLoginResponseDTO.builder()
                    .message(userPath == UserPath.KAKAO ? "카카오 로그인 성공 (기존 회원)" : "네이버 로그인 성공 (기존 회원)")
                    .userInfo(existingUser)
                    .isNewUser(false)
                    .build();
        } else {
            // 신규 회원일 경우 최소 정보로 회원가입
            SocialUserInfo socialUserInfo = SocialUserInfo.builder()
                    .socialUserId(socialUserId)
                    .userPath(userPath)
                    .nickname(userPath == UserPath.KAKAO ? "카카오신규" : "네이버신규")
                    .email(null)
                    .profileImage(null)
                    .gender(null)
                    .build();
            boolean signupResult = socialSignup(socialUserInfo);
            if (signupResult) {
                UserDTO newUser = getUserBySocialInfo(userPath, socialUserId);
                return SocialLoginResponseDTO.builder()
                        .message(userPath == UserPath.KAKAO ? "카카오 회원가입 및 로그인 성공" : "네이버 회원가입 및 로그인 성공")
                        .userInfo(newUser)
                        .isNewUser(true)
                        .build();
            } else {
                return SocialLoginResponseDTO.builder()
                        .message(userPath == UserPath.KAKAO ? "카카오 회원가입에 실패했습니다." : "네이버 회원가입에 실패했습니다.")
                        .userInfo(null)
                        .isNewUser(true)
                        .build();
            }
        }
    }

    @Override
    public UserSignupResponseDTO registerUserWithResponse(UserDTO userDTO) {
        try {
            // 이메일 형식 검증
            if (userDTO.getUserId() == null || !userDTO.getUserId().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return UserSignupResponseDTO.builder()
                    .message("올바르지 않은 이메일 형식입니다.")
                    .success(false)
                    .build();
            }

            // UserDTO를 UserEntity로 변환
            UserEntity user = UserEntity.builder()
                .userId(userDTO.getUserId())
                .userPwd(userDTO.getUserPwd())
                .userNick(userDTO.getUserNick())
                .userGender(userDTO.getUserGender() != null ? userDTO.getUserGender() : Gender.MALE)
                .userPath(userDTO.getUserPath() != null ? userDTO.getUserPath() : UserPath.NORMAL)
                .isActive(true)
                .adminYN(false)
                .userFaceLoginYN(false)
                .build();

            boolean registerUserResult = registerUser(user);
            log.info("회원 가입 요청 - 사용자 ID: {}", userDTO.getUserId());
            log.info("회원 가입 성공 여부: {}", registerUserResult);

            if (registerUserResult) {
                return UserSignupResponseDTO.builder()
                    .message("회원가입이 성공적으로 완료되었습니다.")
                    .success(true)
                    .build();
            } else {
                return UserSignupResponseDTO.builder()
                    .message("이미 존재하는 이메일입니다.")
                    .success(false)
                    .build();
            }
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생", e);
            return UserSignupResponseDTO.builder()
                .message("회원가입 처리 중 오류가 발생했습니다: " + e.getMessage())
                .success(false)
                .build();
        }
    }

    @Override
    public SocialLoginResponseDTO socialLoginWithResponse(String socialToken, UserPath userPath) {
        // TODO: 실제 소셜 API 연동 로직 구현
        // 현재는 기본 구조만 제공
        log.info("소셜 로그인 시도 - 경로: {}", userPath);
        return SocialLoginResponseDTO.builder()
            .message("소셜 로그인 기능은 아직 구현되지 않았습니다.")
            .userInfo(null)
            .isNewUser(false)
            .build();
    }
}




