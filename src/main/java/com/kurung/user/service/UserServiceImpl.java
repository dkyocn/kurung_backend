package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.PasswordChangeRequestDTO;
import com.kurung.user.dto.PasswordChangeResponseDTO;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.common.dto.ApiResponseDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void registerUser(UserDTO userDTO) {
        // 이메일 형식 검증
        if (userDTO.getUserId() == null || !userDTO.getUserId().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }

        // UserDTO를 UserEntity로 변환
        UserEntity user = UserEntity.builder()
            .userId(userDTO.getUserId())
            .userPwd(userDTO.getUserPwd())
            .userNick(userDTO.getUserNick())
            .userGender(userDTO.getUserGender() != null ? userDTO.getUserGender() : Gender.MALE) // 기본값 MALE
            .userPath(userDTO.getUserPath() != null ? userDTO.getUserPath() : UserPath.NORMAL)
            .isActive(true) // 기본값 1(활성)
            .adminYN(false) // 기본값 0(일반사용자)
            .userFaceLoginYN(false) // 기본값 0(FALSE)
            .build();

        if (validateUser(user)) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getUserPwd());
        log.info("회원가입 - 사용자 ID: {}, 비밀번호 암호화 완료", user.getUserId());

        user.assignUserUuid(generateUuid()); // 또는 Builder 내부에서 uuid 설정되도록
        userRepository.save(user);
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

    @Override
    @Transactional
    public PasswordChangeResponseDTO changePassword(String userUuid, PasswordChangeRequestDTO request) {
        try {
            // 1. 사용자 정보 조회
            UserEntity userEntity = userRepository.getUserByUuid(userUuid);
            if (userEntity == null) {
                return PasswordChangeResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .success(false)
                    .build();
            }
            
            // 2. 현재 비밀번호 검증
            if (!passwordEncoder.matches(request.getCurrentPassword(), userEntity.getUserPwd())) {
                return PasswordChangeResponseDTO.builder()
                    .message("현재 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
            }
            
            // 3. 새 비밀번호와 확인 비밀번호 일치 검증
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return PasswordChangeResponseDTO.builder()
                    .message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
            }
            
            // 4. 새 비밀번호가 현재 비밀번호와 다른지 검증
            if (passwordEncoder.matches(request.getNewPassword(), userEntity.getUserPwd())) {
                return PasswordChangeResponseDTO.builder()
                    .message("새 비밀번호는 현재 비밀번호와 달라야 합니다.")
                    .success(false)
                    .build();
            }
            
            // 5. 새 비밀번호 암호화 및 저장
            String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
            userEntity.updatePassword(encodedNewPassword);
            userRepository.save(userEntity);
            
            log.info("비밀번호 변경 성공 - 사용자 UUID: {}", userUuid);
            
            return PasswordChangeResponseDTO.builder()
                .message("비밀번호가 성공적으로 변경되었습니다.")
                .success(true)
                .build();
                
        } catch (Exception e) {
            log.error("비밀번호 변경 중 오류 발생 - 사용자 UUID: {}", userUuid, e);
            return PasswordChangeResponseDTO.builder()
                .message("비밀번호 변경 처리 중 오류가 발생했습니다: " + e.getMessage())
                .success(false)
                .build();
        }
    }

    @Override
    @Transactional
    public PasswordChangeResponseDTO resetPassword(String userUuid, VerificationCodeDTO request) {
        try {
            // 1. 사용자 정보 조회
            UserEntity userEntity = userRepository.getUserByUuid(userUuid);
            if (userEntity == null) {
                return PasswordChangeResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .success(false)
                    .build();
            }
            
            // 2. 새 비밀번호와 확인 비밀번호 일치 검증
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return PasswordChangeResponseDTO.builder()
                    .message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
            }
            
            // 3. 새 비밀번호 암호화 및 저장
            String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
            userEntity.updatePassword(encodedNewPassword);
            userRepository.save(userEntity);
            
            log.info("비밀번호 재설정 성공 - 사용자 UUID: {}", userUuid);
            
            return PasswordChangeResponseDTO.builder()
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .success(true)
                .build();
                
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생 - 사용자 UUID: {}", userUuid, e);
            return PasswordChangeResponseDTO.builder()
                .message("비밀번호 재설정 처리 중 오류가 발생했습니다: " + e.getMessage())
                .success(false)
                .build();
        }
    }

    @Override
    public ApiResponseDTO<String> sendVerificationCode(VerificationCodeDTO request) {
        try {
            // 1. 이메일 형식 검증
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("올바른 이메일 형식이 아닙니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 2. 사용자 존재 여부 확인
            UserEntity userEntity = userRepository.getByUserId(request.getEmail());
            if (userEntity == null) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("등록되지 않은 이메일입니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 3. 인증번호 생성 및 이메일 발송 (실제 구현은 이메일 서비스 연동 필요)
            String verificationCode = generateVerificationCode();
            // TODO: 이메일 서비스로 인증번호 발송
            log.info("인증번호 발송 - 이메일: {}, 인증번호: {}", request.getEmail(), verificationCode);
            
            return ApiResponseDTO.<String>builder()
                .success(true)
                .message("인증번호가 이메일로 발송되었습니다.")
                .data(verificationCode) // 실제로는 이메일로만 발송하고 여기서는 반환하지 않음
                .timestamp(LocalDateTime.now())
                .build();
                
        } catch (Exception e) {
            log.error("인증번호 발송 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder()
                .success(false)
                .message("인증번호 발송 처리 중 오류가 발생했습니다: " + e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        }
    }

    @Override
    public ApiResponseDTO<String> confirmVerificationCode(VerificationCodeDTO request) {
        try {
            // 1. 이메일 형식 검증
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("올바른 이메일 형식이 아닙니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 2. 인증번호 형식 검증 (6자리 숫자)
            if (request.getVerificationCode() == null || !request.getVerificationCode().matches("^\\d{6}$")) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("인증번호는 6자리 숫자여야 합니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 3. 사용자 존재 여부 확인
            UserEntity userEntity = userRepository.getByUserId(request.getEmail());
            if (userEntity == null) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("등록되지 않은 이메일입니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // TODO: 실제 인증번호 검증 로직 (Redis나 세션에 저장된 인증번호와 비교)
            // 현재는 임시로 "123456"을 올바른 인증번호로 가정
            if ("123456".equals(request.getVerificationCode())) {
                log.info("인증번호 확인 성공 - 이메일: {}", request.getEmail());
                return ApiResponseDTO.<String>builder()
                    .success(true)
                    .message("인증번호가 확인되었습니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            } else {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("인증번호가 일치하지 않습니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
                
        } catch (Exception e) {
            log.error("인증번호 확인 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder()
                .success(false)
                .message("인증번호 확인 처리 중 오류가 발생했습니다: " + e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        }
    }

    @Override
    @Transactional
    public ApiResponseDTO<String> resetPasswordByEmail(VerificationCodeDTO request) {
        try {
            // 1. 이메일 형식 검증
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("올바른 이메일 형식이 아닙니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 2. 사용자 존재 여부 확인
            UserEntity userEntity = userRepository.getByUserId(request.getEmail());
            if (userEntity == null) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("등록되지 않은 이메일입니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 3. 새 비밀번호와 확인 비밀번호 일치 검증
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return ApiResponseDTO.<String>builder()
                    .success(false)
                    .message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.")
                    .timestamp(LocalDateTime.now())
                    .build();
            }
            
            // 4. 새 비밀번호 암호화 및 저장
            String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
            userEntity.updatePassword(encodedNewPassword);
            userRepository.save(userEntity);
            
            log.info("비밀번호 재설정 성공 - 이메일: {}", request.getEmail());
            
            return ApiResponseDTO.<String>builder()
                .success(true)
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .timestamp(LocalDateTime.now())
                .build();
                
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder()
                .success(false)
                .message("비밀번호 재설정 처리 중 오류가 발생했습니다: " + e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        }
    }

    // 인증번호 생성 메서드
    private String generateVerificationCode() {
        return String.format("%06d", (int)(Math.random() * 1000000));
    }
}




