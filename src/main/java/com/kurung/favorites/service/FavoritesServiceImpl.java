package com.kurung.favorites.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.repository.FavoritesRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

  private final FavoritesRepository favoritesRepository;

  @Override
  public FavoritesDTO getFavoriteById(int id) {
    List<FavoritesEntity> favoritesById = favoritesRepository.getFavoritesById(id);

//    if(favoritesById.isEmpty()){
//      throw new CustomIllegalArgumentException(CustomHttpStatus.FAVORITE_NOT_FOUND);
//    }
//
//    return (FavoritesDTO) favoritesById.stream().map(favoritesEntity -> FavoritesDTO.toFavoritesBuilder().favoritesEntity(favoritesEntity).build()).collect(Collectors.toList());
//  }

    if (favoritesById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.FAVORITE_NOT_FOUND);
    }

    // 첫 번째 결과만 반환
    return FavoritesDTO.toFavoritesBuilder()
        .favoritesEntity(favoritesById.get(0))
        .build();
  }
}
