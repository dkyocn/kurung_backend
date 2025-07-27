package com.kurung.favorites.repository;

import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.enumeration.FavoritesType;
import java.util.List;

public interface FavoritesRepositorySupport {

  List<FavoritesEntity> getFavoritesById();

  List<FavoritesEntity> getFavoritesList(String userUuid, FavoritesType favoritesType);

  FavoritesEntity getFavoriteById(int id);

  List<FavoritesEntity> getAllFavorites(int id, FavoritesType favoritesType);
}
