package com.kurung.favorites.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.common.security.service.SessionService;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.enumeration.FavoritesType;
import com.kurung.favorites.repository.FavoritesRepository;
import com.kurung.user.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

  private final FavoritesRepository favoritesRepository;
  private final SessionService sessionService;

  @Override
  @Transactional
  public FavoritesDTO createFavorite(FavoritesDTO favoritesDTO) {

    UserDTO userDTO = sessionService.getUserFromToken();

    try {
      // 건강정보 저장
      FavoritesEntity entity = favoritesRepository.save(
          FavoritesEntity.createHealthInfoBuilder()
              .favoritesDTO(favoritesDTO)
              .userDTO(userDTO)
              .build()
      );

      return FavoritesDTO.toFavoritesBuilder().favoritesEntity(entity).build();

    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.FAVORITE_SAVE_ERROR);
    }
  }

  @Override
  public List<FavoritesDTO> getFavoritesList(FavoritesType favoritesType) {

    // 1. 사용자 확인
    UserDTO userDTO = sessionService.getUserFromToken();

    // 2. 즐겨찾기 목록 조회
    List<FavoritesEntity> favoritesList = favoritesRepository.getFavoritesList(userDTO.getUserUuid(), favoritesType);

    // 3. DTO로 변환하여 리턴
    return favoritesList.stream()
        .map(FavoritesDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteFavorite(int id) {
    // 삭제할 즐겨찾기 조회
    FavoritesEntity favoritesEntity = favoritesRepository.getFavoriteById(id);

    if (favoritesEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.FAVORITE_NOT_FOUND);
    }

    try {
      favoritesRepository.delete(favoritesEntity);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.FAVORITES_DELETE_ERROR);
    }
  }

  @Override
  public List<FavoritesDTO> getALLFavoritesList(int id, FavoritesType favoritesType) {

    List<FavoritesEntity> favoritesEntityList = favoritesRepository.getAllFavorites(id, favoritesType);

    return favoritesEntityList.stream().map(FavoritesDTO::new).collect(Collectors.toList());
  }


}
