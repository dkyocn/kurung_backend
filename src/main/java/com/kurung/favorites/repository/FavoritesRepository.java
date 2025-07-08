package com.kurung.favorites.repository;

import com.kurung.favorites.entity.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Integer>, FavoritesRepositorySupport {
}
