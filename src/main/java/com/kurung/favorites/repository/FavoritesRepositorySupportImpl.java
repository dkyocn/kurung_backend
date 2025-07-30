package com.kurung.favorites.repository;

import static com.kurung.favorites.entity.QFavoritesEntity.favoritesEntity;

import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.favorites.enumeration.FavoritesType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
      case FOOD -> favoritesEntity.foods.isNotNull();
      case ROUTINES -> favoritesEntity.routines.isNotNull();
      case COMMUNITY -> favoritesEntity.community.isNotNull();
    };
  }

  @Override
  public FavoritesEntity getFavoriteById(int id) {
    return jpaQueryFactory
        .selectFrom(favoritesEntity)
        .where(favoritesEntity.favoritesId.eq(id))
        .fetchOne();
  }

  @Override
  public List<FavoritesEntity> getAllFavorites(int id, FavoritesType favoritesType) {
    return jpaQueryFactory.selectFrom(favoritesEntity)
        .where(eqFavoritesTypeId(id, favoritesType))
        .fetch();
  }

  BooleanExpression eqFavoritesTypeId(int id, FavoritesType favoritesType) {
    return switch (favoritesType) {
      case FOOD -> favoritesEntity.foods.foodId.eq(id);
      case ROUTINES -> favoritesEntity.routines.routinesId.eq(id);
      case COMMUNITY -> favoritesEntity.community.communityId.eq(id);
    };
  }


}
