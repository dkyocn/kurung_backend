package com.kurung.favorites.repository;

import com.kurung.favorites.entity.FavoritesEntity;
import java.util.List;

public interface FavoritesRepositorySupport {

  List<FavoritesEntity> getFavoritesById();
}
