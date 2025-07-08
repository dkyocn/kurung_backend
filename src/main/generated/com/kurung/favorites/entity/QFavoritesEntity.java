package com.kurung.favorites.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoritesEntity is a Querydsl query type for FavoritesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoritesEntity extends EntityPathBase<FavoritesEntity> {

    private static final long serialVersionUID = -1201731519L;

    public static final QFavoritesEntity favoritesEntity = new QFavoritesEntity("favoritesEntity");

    public final NumberPath<Integer> communityId = createNumber("communityId", Integer.class);

    public final NumberPath<Integer> favoritesId = createNumber("favoritesId", Integer.class);

    public final NumberPath<Integer> recipeId = createNumber("recipeId", Integer.class);

    public final NumberPath<Integer> routinesId = createNumber("routinesId", Integer.class);

    public final NumberPath<Integer> stressReliefId = createNumber("stressReliefId", Integer.class);

    public final StringPath userUuid = createString("userUuid");

    public QFavoritesEntity(String variable) {
        super(FavoritesEntity.class, forVariable(variable));
    }

    public QFavoritesEntity(Path<? extends FavoritesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoritesEntity(PathMetadata metadata) {
        super(FavoritesEntity.class, metadata);
    }

}

