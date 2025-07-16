package com.kurung.favorites.service;

import com.kurung.favorites.dto.FavoritesDTO;
import java.util.List;

public interface FavoritesService {

  List<FavoritesDTO> getFavoriteList();

  void createFavorite(FavoritesDTO favoritesDTO);

  List<FavoritesDTO> getFavoritesList(String userUuid);
}
