package com.kurung.user.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    @Override
    @Transactional
    public void updateRefresh(UserDTO userDTO, String refreshToken) {
        // 1. DB 조회
        UserEntity userEntity = userRepository.getUserByUuid(userDTO.getUserUuid());
        // 2. null 체크
        if(userEntity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND);
        }

        if((userEntity.getUserRefreshToken() == null) || (!userEntity.getUserRefreshToken().equals(refreshToken))){
            // 3. 업데이트
            userEntity.updateRefresh(refreshToken);
        }
    }
}




