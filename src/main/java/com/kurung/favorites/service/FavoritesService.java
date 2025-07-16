package com.kurung.favorites.service;

import com.kurung.common.enumeration.HealthType;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.enumeration.FavoritesType;
import java.util.List;

public interface FavoritesService {

  void createFavorite(FavoritesDTO favoritesDTO);

  List<FavoritesDTO> getFavoritesList(String userUuid, FavoritesType favoritesType);

  void deleteFavorite(int id);
}
