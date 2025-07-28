package com.kurung.favorites.service;

import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.enumeration.FavoritesType;
import java.util.List;

public interface FavoritesService {

  FavoritesDTO createFavorite(FavoritesDTO favoritesDTO);

  List<FavoritesDTO> getFavoritesList(FavoritesType favoritesType);

  void deleteFavorite(int id);

  List<FavoritesDTO> getALLFavoritesList(int id, FavoritesType favoritesType);
}
