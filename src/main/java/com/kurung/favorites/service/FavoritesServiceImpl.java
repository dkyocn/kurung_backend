package com.kurung.favorites.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.enumeration.FavoritesType;
import com.kurung.favorites.repository.FavoritesRepository;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.kurung.user.controller.UserController;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

  private final FavoritesRepository favoritesRepository;
  private final UserService userService;

  @Override
  @Transactional
  public void createFavorite(FavoritesDTO favoritesDTO) {

    UserDTO userByUuid = userService.getUserByUuid(favoritesDTO.getUserDTO().getUserUuid());

    try {
      // 건강정보 저장
      FavoritesEntity entity = favoritesRepository.save(
          FavoritesEntity.createHealthInfoBuilder()
              .favoritesDTO(favoritesDTO)
              .userDTO(userByUuid)
              .build()
      );

    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.FAVORITE_SAVE_ERROR);
    }
  }

  @Override
  public List<FavoritesDTO> getFavoritesList(String userUuid, FavoritesType favoritesType) {

    // 1. 사용자 확인
    UserDTO userDTO = userService.getUserByUuid(userUuid);

    // 2. 즐겨찾기 목록 조회
    List<FavoritesEntity> favoritesList = favoritesRepository.getFavoritesList(userUuid, favoritesType);

    // 3. DTO로 변환하여 리턴
    return favoritesList.stream()
        .map(FavoritesDTO::new)
        .collect(Collectors.toList());
  }

}
