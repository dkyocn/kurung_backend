package com.kurung.favorites.repository;

import com.kurung.favorites.entity.FavoritesEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kurung.favorites.entity.QFavoritesEntity.favoritesEntity;

@Repository
@RequiredArgsConstructor
public class FavoritesRepositorySupportImpl implements FavoritesRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

//  public List<FavoritesEntity> getFavoritesByUser(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(favoritesEntity)
//        .where(favoritesEntity.userUuid.eq(uuid))
//        .fetch();
//  }
//
//  public FavoritesEntity getLatestFavorite(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(favoritesEntity)
//        .where(favoritesEntity.userUuid.eq(uuid))
//        .orderBy(favoritesEntity.favoritesId.desc())
//        .limit(1)
//        .fetchOne();
//  }

  @Override
  public List<FavoritesEntity> getFavoritesById(int id) {
    return null;
  }
}
