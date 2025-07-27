package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.PasswordResetRequestDTO;
import com.kurung.user.dto.PasswordResetResponseDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.common.dto.ApiResponseDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.common.email.service.EmailService;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserDTO getUserByUuid(String userUuid) {
        UserEntity userEntity = userRepository.getUserByUuid(userUuid);
        if (userEntity == null) {
            return null;
        }
        return UserDTO.builder()
                .userUuid(userEntity.getUserUuid())
                .userPwd(userEntity.getUserPwd())
                .userId(userEntity.getUserId())
                .userNick(userEntity.getUserNick())
                .userGender(userEntity.getUserGender())
                .userPath(userEntity.getUserPath())
                .isActive(userEntity.isActive())
                .adminYN(userEntity.isAdminYN())
                .userFaceLoginYN(userEntity.isUserFaceLoginYN())
                .build();
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.getByUserId(userId);
        if (userEntity == null) {
            return null;
        }
        return UserDTO.builder()
                .userUuid(userEntity.getUserUuid())
                .userId(userEntity.getUserId())
                .userPwd(userEntity.getUserPwd())
                .userNick(userEntity.getUserNick())
                .userGender(userEntity.getUserGender())
                .userPath(userEntity.getUserPath())
                .isActive(userEntity.isActive())
                .adminYN(userEntity.isAdminYN())
                .userFaceLoginYN(userEntity.isUserFaceLoginYN())
                .build();
    }

    @Override
    public void updateRefresh(UserDTO userDTO, String refreshToken) {
        UserEntity userEntity = userRepository.getUserByUuid(userDTO.getUserUuid());
        if (userEntity != null) {
            userEntity.updateRefresh(refreshToken);
            userRepository.save(userEntity);
        }
    }

    @Override
    public boolean checkUserIdAvailability(String userId) {
        UserEntity userEntity = userRepository.getByUserId(userId);
        return userEntity == null;
    }

    @Override
    @Transactional
    public void registerUser(UserDTO userDTO) {
        if (userDTO.getUserId() == null || !userDTO.getUserId().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
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
        if (validateUser(user)) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        String encodedPassword = passwordEncoder.encode(user.getUserPwd());
        log.info("회원가입 - 사용자 ID: {}, 비밀번호 암호화 완료", user.getUserId());
        user.assignUserUuid(generateUuid());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public PasswordResetResponseDTO resetPassword(String userUuid, PasswordResetRequestDTO request) {
        UserEntity userEntity = userRepository.getUserByUuid(userUuid);
        if (userEntity == null) {
            return PasswordResetResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .success(false)
                    .build();
        }

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(request.getCurrentPassword(), userEntity.getUserPwd())) {
            return PasswordResetResponseDTO.builder()
                    .message("현재 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
        }

        // 새 비밀번호 확인
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return PasswordResetResponseDTO.builder()
                    .message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
        }

        // 새 비밀번호 암호화 및 저장
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        userEntity.updatePassword(encodedNewPassword);
        userRepository.save(userEntity);

        log.info("비밀번호 재설정 성공 - 사용자 UUID: {}", userUuid);
        return PasswordResetResponseDTO.builder()
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .success(true)
                .build();
    }

    @Override
    @Transactional
    public PasswordResetResponseDTO resetPassword(String userUuid, VerificationCodeDTO request) {
        UserEntity userEntity = userRepository.getUserByUuid(userUuid);
        if (userEntity == null) {
            return PasswordResetResponseDTO.builder()
                    .message("사용자를 찾을 수 없습니다.")
                    .success(false)
                    .build();
        }

        // 새 비밀번호 확인
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return PasswordResetResponseDTO.builder()
                    .message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.")
                    .success(false)
                    .build();
        }

        // 새 비밀번호 암호화 및 저장
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        userEntity.updatePassword(encodedNewPassword);
        userRepository.save(userEntity);

        log.info("비밀번호 재설정 성공 - 사용자 UUID: {}", userUuid);
        return PasswordResetResponseDTO.builder()
                .message("비밀번호가 성공적으로 재설정되었습니다.")
                .success(true)
                .build();
    }

    @Override
    public ApiResponseDTO<String> sendVerificationCode(VerificationCodeDTO request) {
        try {
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponseDTO.<String>builder().success(false).message("올바른 이메일 형식이 아닙니다.").timestamp(LocalDateTime.now()).build();
            }
            if ("PASSWORD_RESET".equals(request.getVerificationType())) {
                UserEntity userEntity = userRepository.getByUserId(request.getEmail());
                if (userEntity == null) {
                    return ApiResponseDTO.<String>builder().success(false).message("등록되지 않은 이메일입니다.").timestamp(LocalDateTime.now()).build();
                }
            } else if ("SIGNUP".equals(request.getVerificationType())) {
                UserEntity userEntity = userRepository.getByUserId(request.getEmail());
                if (userEntity != null) {
                    return ApiResponseDTO.<String>builder().success(false).message("이미 등록된 이메일입니다.").timestamp(LocalDateTime.now()).build();
                }
            }
            emailService.sendVerificationEmail(request.getEmail());
            log.info("인증번호 발송 성공 - 이메일: {}, 타입: {}", request.getEmail(), request.getVerificationType());
            return ApiResponseDTO.<String>builder().success(true).message("인증번호가 이메일로 발송되었습니다.").timestamp(LocalDateTime.now()).build();
        } catch (Exception e) {
            log.error("인증번호 발송 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder().success(false).message("인증번호 발송 처리 중 오류가 발생했습니다: " + e.getMessage()).timestamp(LocalDateTime.now()).build();
        }
    }

    @Override
    public ApiResponseDTO<String> confirmVerificationCode(VerificationCodeDTO request) {
        try {
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ApiResponseDTO.<String>builder().success(false).message("올바른 이메일 형식이 아닙니다.").timestamp(LocalDateTime.now()).build();
            }
            if (request.getVerificationCode() == null || !request.getVerificationCode().matches("^\\d{6}$")) {
                return ApiResponseDTO.<String>builder().success(false).message("인증번호는 6자리 숫자여야 합니다.").timestamp(LocalDateTime.now()).build();
            }
            UserEntity userEntity = userRepository.getByUserId(request.getEmail());
            if (userEntity == null) {
                return ApiResponseDTO.<String>builder().success(false).message("등록되지 않은 이메일입니다.").timestamp(LocalDateTime.now()).build();
            }
            boolean isVerified = emailService.verifyCode(request.getEmail(), request.getVerificationCode());
            if (isVerified) {
                log.info("인증번호 확인 성공 - 이메일: {}", request.getEmail());
                return ApiResponseDTO.<String>builder().success(true).message("인증번호가 확인되었습니다.").timestamp(LocalDateTime.now()).build();
            } else {
                return ApiResponseDTO.<String>builder().success(false).message("인증번호가 일치하지 않거나 만료되었습니다.").timestamp(LocalDateTime.now()).build();
            }
        } catch (Exception e) {
            log.error("인증번호 확인 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder().success(false).message("인증번호 확인 처리 중 오류가 발생했습니다: " + e.getMessage()).timestamp(LocalDateTime.now()).build();
        }
    }

    @Override
    @Transactional
    public ApiResponseDTO<String> resetPasswordByEmail(VerificationCodeDTO request) {
        try {
            log.info("비밀번호 재설정 시작 - 이메일: {}", request.getEmail());
            
            if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                log.warn("올바르지 않은 이메일 형식: {}", request.getEmail());
                return ApiResponseDTO.<String>builder().success(false).message("올바른 이메일 형식이 아닙니다.").timestamp(LocalDateTime.now()).build();
            }
            
            UserEntity userEntity = userRepository.getByUserId(request.getEmail());
            if (userEntity == null) {
                log.warn("등록되지 않은 이메일: {}", request.getEmail());
                return ApiResponseDTO.<String>builder().success(false).message("등록되지 않은 이메일입니다.").timestamp(LocalDateTime.now()).build();
            }
            
            log.info("사용자 조회 성공 - UUID: {}, 현재 비밀번호: {}", userEntity.getUserUuid(), userEntity.getUserPwd());
            
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                log.warn("비밀번호 불일치 - 새 비밀번호: {}, 확인 비밀번호: {}", request.getNewPassword(), request.getConfirmPassword());
                return ApiResponseDTO.<String>builder().success(false).message("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.").timestamp(LocalDateTime.now()).build();
            }
            
            String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
            log.info("새 비밀번호 암호화 완료 - 원본: {}, 암호화: {}", request.getNewPassword(), encodedNewPassword);
            
            userEntity.updatePassword(encodedNewPassword);
            log.info("비밀번호 업데이트 완료 - 새 암호화 비밀번호: {}", userEntity.getUserPwd());
            
            UserEntity savedEntity = userRepository.save(userEntity);
            log.info("DB 저장 완료 - 저장된 비밀번호: {}", savedEntity.getUserPwd());
            
            log.info("비밀번호 재설정 성공 - 이메일: {}", request.getEmail());
            return ApiResponseDTO.<String>builder().success(true).message("비밀번호가 성공적으로 재설정되었습니다.").timestamp(LocalDateTime.now()).build();
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생 - 이메일: {}", request.getEmail(), e);
            return ApiResponseDTO.<String>builder().success(false).message("비밀번호 재설정 처리 중 오류가 발생했습니다: " + e.getMessage()).timestamp(LocalDateTime.now()).build();
        }
    }

    private boolean validateUser(UserEntity user) {
        return userRepository.getByUserId(user.getUserId()) != null;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}




