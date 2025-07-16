package com.kurung.favorites.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.enumeration.FavoritesType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kurung.favorites.entity.QFavoritesEntity.favoritesEntity;

@Repository
@RequiredArgsConstructor
public class FavoritesRepositorySupportImpl implements FavoritesRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public List<FavoritesEntity> getFavoritesByUser(String uuid) {
    return jpaQueryFactory
        .selectFrom(favoritesEntity)
//        .where(favoritesEntity.userUuid.eq(uuid))
        .where(favoritesEntity.user.userUuid.eq(uuid))
        .fetch();
  }

  public FavoritesEntity getLatestFavorite(String uuid) {
    return jpaQueryFactory
        .selectFrom(favoritesEntity)
//        .where(favoritesEntity.userUuid.eq(uuid))
        .where(favoritesEntity.user.userUuid.eq(uuid))
        .orderBy(favoritesEntity.favoritesId.desc())
        .limit(1)
        .fetchOne();
  }

  @Override
  public List<FavoritesEntity> getFavoritesById() {
    return jpaQueryFactory
        .selectFrom(favoritesEntity)
        .fetch();
  }

  @Override
  public List<FavoritesEntity> getFavoritesList(String userUuid, FavoritesType favoritesType ) {
    return jpaQueryFactory
        .selectFrom(favoritesEntity)
        .where(
            favoritesEntity.user.userUuid.eq(userUuid),notNullFavoritesType(favoritesType)
        )
        .fetch();
  }

  BooleanExpression notNullFavoritesType(FavoritesType favoritesType) {
    return switch (favoritesType) {
      case STRESS -> favoritesEntity.stressRelief.isNotNull();
      case RECIPE -> favoritesEntity.recipe.isNotNull();
      case ROUTINES -> favoritesEntity.routines.isNotNull();
      case COMMUNITY -> favoritesEntity.community.isNotNull();
    };
  }
}
