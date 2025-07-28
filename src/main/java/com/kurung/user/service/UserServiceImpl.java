package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.repository.UserRepository;
import com.kurung.common.email.service.EmailService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kurung.user.social.client.KakaoOAuthClient;
import com.kurung.user.social.dto.KakaoUserInfo;
import com.kurung.common.util.JWTUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final KakaoOAuthClient kakaoOAuthClient;
    private final JWTUtil jwtUtil;

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
    public boolean socialSignup(UserDTO socialUserInfo) {
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
    public UserDTO registerUserWithResponse(UserDTO userDTO) {
        try {
            // 이메일 형식 검증
            if (userDTO.getUserId() == null || !userDTO.getUserId().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return UserDTO.builder()
                    .message("올바르지 않은 이메일 형식입니다.")
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
                return UserDTO.builder()
                    .message("회원가입이 성공적으로 완료되었습니다.")
                    .build();
            } else {
                return UserDTO.builder()
                    .message("이미 존재하는 이메일입니다.")
                    .build();
            }
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생", e);
            return UserDTO.builder()
                .message("회원가입 처리 중 오류가 발생했습니다: " + e.getMessage())
                .build();
        }
    }

    @Override
    public UserDTO socialLoginWithResponse(String socialToken, UserPath userPath) {
        try {
            log.info("실제 소셜 로그인 시작 - 경로: {}", userPath);

            if (userPath == UserPath.KAKAO) {
                return processKakaoLogin(socialToken);
            } else if (userPath == UserPath.NAVER) {
                return processNaverLogin(socialToken);
            } else {
                return UserDTO.builder()
                    .message("지원하지 않는 소셜 로그인 경로입니다.")
                    .isNewUser(false)
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
            }
        } catch (Exception e) {
            log.error("소셜 로그인 처리 중 오류 발생", e);
            return UserDTO.builder()
                .message("소셜 로그인 처리 중 오류가 발생했습니다: " + e.getMessage())
                .isNewUser(false)
                .accessToken(null)
                .refreshToken(null)
                .build();
        }
    }

    /**
     * 카카오 로그인 처리
     */
    private UserDTO processKakaoLogin(String socialToken) {
        try {
            // 카카오 사용자 정보 조회
            KakaoUserInfo kakaoUserInfo = kakaoOAuthClient.getUserInfo(socialToken);

            if (kakaoUserInfo == null || kakaoUserInfo.getId() == null) {
                return UserDTO.builder()
                    .message("카카오 사용자 정보를 가져올 수 없습니다.")
                    .isNewUser(false)
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
            }

            String socialUserId = String.valueOf(kakaoUserInfo.getId());
            String email = kakaoUserInfo.getKakaoAccount() != null ?
                kakaoUserInfo.getKakaoAccount().getEmail() : null;
            String nickname = kakaoUserInfo.getProperties() != null ?
                kakaoUserInfo.getProperties().getNickname() : "카카오사용자";
            String profileImage = kakaoUserInfo.getProperties() != null ?
                kakaoUserInfo.getProperties().getProfileImage() : null;

            // 기존 사용자 확인
            UserEntity existingUser = userRepository.findByUserPathAndUserKey(UserPath.KAKAO, socialUserId);

            if (existingUser != null) {
                // 기존 사용자 로그인
                UserDTO userDTO = UserDTO.toUserBuilder()
                    .userEntity(existingUser)
                    .build();

                log.info("카카오 기존 사용자 로그인 성공 - 사용자ID: {}", existingUser.getUserId());

                // JWT 토큰 생성
                String accessToken = jwtUtil.generateToken(userDTO, "access");
                String refreshToken = jwtUtil.generateToken(userDTO, "refresh");

                // 리프레시 토큰 업데이트
                updateRefresh(userDTO, refreshToken);

                // UserDTO 빌더로 응답 정보 설정
                return UserDTO.builder()
                    .userUuid(userDTO.getUserUuid())
                    .userId(userDTO.getUserId())
                    .userFaceLoginYN(userDTO.isUserFaceLoginYN())
                    .userFaceLoginRef(userDTO.getUserFaceLoginRef())
                    .userPwd(userDTO.getUserPwd())
                    .userNick(userDTO.getUserNick())
                    .userGender(userDTO.getUserGender())
                    .userAge(userDTO.getUserAge())
                    .userKey(userDTO.getUserKey())
                    .userPath(userDTO.getUserPath())
                    .profileImg(userDTO.getProfileImg())
                    .isActive(userDTO.isActive())
                    .adminYN(userDTO.isAdminYN())
                    .userRefreshToken(userDTO.getUserRefreshToken())
                    .message("카카오 로그인 성공")
                    .isNewUser(false)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            } else {
                // 신규 사용자 회원가입
                boolean signupResult = registerKakaoUser(socialUserId, email, nickname, profileImage);

                if (signupResult) {
                    UserEntity newUser = userRepository.findByUserPathAndUserKey(UserPath.KAKAO, socialUserId);
                    UserDTO userDTO = UserDTO.toUserBuilder()
                        .userEntity(newUser)
                        .build();

                    log.info("카카오 신규 사용자 회원가입 및 로그인 성공 - 사용자ID: {}", newUser.getUserId());

                    // JWT 토큰 생성
                    String accessToken = jwtUtil.generateToken(userDTO, "access");
                    String refreshToken = jwtUtil.generateToken(userDTO, "refresh");

                    // 리프레시 토큰 업데이트
                    updateRefresh(userDTO, refreshToken);

                    // UserDTO 빌더로 응답 정보 설정
                    return UserDTO.builder()
                        .userUuid(userDTO.getUserUuid())
                        .userId(userDTO.getUserId())
                        .userFaceLoginYN(userDTO.isUserFaceLoginYN())
                        .userFaceLoginRef(userDTO.getUserFaceLoginRef())
                        .userPwd(userDTO.getUserPwd())
                        .userNick(userDTO.getUserNick())
                        .userGender(userDTO.getUserGender())
                        .userAge(userDTO.getUserAge())
                        .userKey(userDTO.getUserKey())
                        .userPath(userDTO.getUserPath())
                        .profileImg(userDTO.getProfileImg())
                        .isActive(userDTO.isActive())
                        .adminYN(userDTO.isAdminYN())
                        .userRefreshToken(userDTO.getUserRefreshToken())
                        .message("카카오 회원가입 및 로그인 성공")
                        .isNewUser(true)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                } else {
                    return UserDTO.builder()
                        .message("카카오 회원가입에 실패했습니다.")
                        .isNewUser(true)
                        .accessToken(null)
                        .refreshToken(null)
                        .build();
                }
            }
        } catch (Exception e) {
            log.error("카카오 로그인 처리 중 오류 발생", e);
            return UserDTO.builder()
                .message("카카오 로그인 처리 중 오류가 발생했습니다: " + e.getMessage())
                .isNewUser(false)
                .accessToken(null)
                .refreshToken(null)
                .build();
        }
    }

    /**
     * 네이버 로그인 처리 (향후 구현)
     */
    private UserDTO processNaverLogin(String socialToken) {
        return UserDTO.builder()
            .message("네이버 로그인은 아직 구현되지 않았습니다.")
            .isNewUser(false)
            .accessToken(null)
            .refreshToken(null)
            .build();
    }

    /**
     * 카카오 사용자 회원가입
     */
    private boolean registerKakaoUser(String socialUserId, String email, String nickname, String profileImage) {
        try {
            log.info("카카오 사용자 회원가입 시작 - 소셜ID: {}", socialUserId);

            // UUID 생성
            String uuid = generateUuid();

            // 기본 비밀번호 생성 (소셜 로그인은 비밀번호가 필요없지만 시스템상 필요)
            String defaultPassword = "kakao_" + System.currentTimeMillis();
            String encodedPassword = passwordEncoder.encode(defaultPassword);

            // 사용자 ID 설정 (이메일이 있으면 이메일, 없으면 카카오_ID)
            String userId = email != null ? email : "kakao_" + socialUserId;

            UserEntity userEntity = UserEntity.builder()
                .userUuid(uuid)
                .userId(userId)
                .userPwd(encodedPassword)
                .userNick(nickname != null ? nickname : "카카오사용자")
                .userKey(socialUserId)
                .userPath(UserPath.KAKAO)
                .profileImg(profileImage)
                .isActive(true)
                .adminYN(false)
                .userFaceLoginYN(false)
                .build();

            userRepository.save(userEntity);
            log.info("카카오 사용자 회원가입 완료 - 사용자ID: {}", userId);
            return true;
        } catch (Exception e) {
            log.error("카카오 사용자 회원가입 실패", e);
            return false;
        }
    }

    // 비밀번호 재설정 관련 메서드들 (UserDTO로 통합)
        @Override
    public void sendVerificationCode(UserDTO request) {
        try {
            log.info("인증번호 발송 시작 - 이메일: {}", request.getEmail());
            emailService.sendVerificationEmail(request.getEmail());
            log.info("인증번호 발송 완료 - 이메일: {}", request.getEmail());
        } catch (Exception e) {
            log.error("인증번호 발송 실패 - 이메일: {}, 오류: {}", request.getEmail(), e.getMessage(), e);
            throw new RuntimeException("인증번호 발송에 실패했습니다.", e);
        }
    }
    
    @Override
    public boolean confirmVerificationCode(UserDTO request) {
        try {
            log.info("인증번호 확인 시작 - 이메일: {}", request.getEmail());
            // verificationCode는 별도 파라미터로 받아야 함
            boolean isValid = emailService.verifyCode(request.getEmail(), "verification_code");
            log.info("인증번호 확인 완료 - 이메일: {}, 결과: {}", request.getEmail(), isValid);
            return isValid;
        } catch (Exception e) {
            log.error("인증번호 확인 실패 - 이메일: {}, 오류: {}", request.getEmail(), e.getMessage(), e);
            return false;
        }
    }

    @Override
    public UserDTO resetPasswordByEmail(UserDTO request) {
        String userId = null;
        try {
            userId = request.getUserId();

            log.info("비밀번호 재설정 시작 - 사용자 ID: {}", userId);

            // 사용자 조회
            UserEntity userEntity = userRepository.getByUserId(userId);
                        if (userEntity == null) {
                return UserDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .build();
            }
            
            // 새 비밀번호 유효성 검사
            if (request.getNewPassword() == null || request.getNewPassword().trim().isEmpty()) {
                return UserDTO.builder()
                    .message("새 비밀번호를 입력해주세요.")
                    .build();
            }
            
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return UserDTO.builder()
                    .message("비밀번호가 일치하지 않습니다.")
                    .build();
            }

            // 비밀번호 업데이트
            String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
            UserEntity updatedUser = UserEntity.builder()
                .userUuid(userEntity.getUserUuid())
                .userId(userEntity.getUserId())
                .userFaceLoginYN(userEntity.isUserFaceLoginYN())
                .userFaceLoginRef(userEntity.getUserFaceLoginRef())
                .userPwd(encodedNewPassword)
                .userNick(userEntity.getUserNick())
                .userGender(userEntity.getUserGender())
                .userAge(userEntity.getUserAge())
                .userKey(userEntity.getUserKey())
                .userPath(userEntity.getUserPath())
                .profileImg(userEntity.getProfileImg())
                .isActive(userEntity.isActive())
                .adminYN(userEntity.isAdminYN())
                .userRefreshToken(userEntity.getUserRefreshToken())
                .build();
            userRepository.save(updatedUser);

            log.info("비밀번호 재설정 완료 - 사용자 ID: {}", userId);

            return UserDTO.builder()
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .build();
        } catch (Exception e) {
            log.error("비밀번호 재설정 실패 - 사용자 ID: {}, 오류: {}", userId, e.getMessage(), e);
            return UserDTO.builder()
                .message("비밀번호 재설정에 실패했습니다.")
                .build();
        }
    }
}




