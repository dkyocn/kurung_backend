package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean checkUserIdAvailability(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    @Override
    public UserDTO getUserByUuid(String userUuid) {
        UserEntity userEntity = userRepository.getUserByUuid(userUuid);

        if(userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }

        return UserDTO.toUserBuilder()
                .userEntity(userEntity)
                .build();
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.getByUserId(userId);
        if( userEntity == null ) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        return UserDTO.toUserBuilder()
                .userEntity(userEntity)
                .build();
    }
    private String generateUuid() {
        return String.valueOf(System.currentTimeMillis()); // 예: 20250722103759 같은 값
    }

    //아이디 체크
    @Override
    @Transactional
    public boolean registerUser(UserEntity user) {
        if (validateUser(user)) {
            return false;
        }
        user.assignUserUuid(generateUuid()); // 또는 Builder 내부에서 uuid 설정되도록
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public void updateRefresh(UserDTO userDTO, String refreshToken) {
        UserEntity userEntity = userRepository.getUserByUuid(userDTO.getUserUuid());
        if(userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }
        if((userEntity.getUserRefreshToken() == null) || (!userEntity.getUserRefreshToken().equals(refreshToken))){
            userEntity.updateRefresh(refreshToken);
        }
    }

    //중복 유저 검사 필요
    public boolean validateUser(UserEntity user) {
        Optional<UserEntity> findUser = userRepository.findById(user.getUserId());
        return findUser.isPresent();
    }
    // 로그인 체크 메소드
    public UserEntity loginCheck(UserEntity user) {
        Optional<UserEntity> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()) {
            UserEntity findUser = optionalUser.get();
            if (findUser.getUserPwd().equals(user.getUserPwd())) {
                return findUser;
            }
        }
        return null;
    }
    //세션성공했을 시
    public void createSession(HttpServletRequest request, UserEntity user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }
}




