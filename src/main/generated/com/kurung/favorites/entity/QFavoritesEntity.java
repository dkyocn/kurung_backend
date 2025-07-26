package com.kurung.favorites.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoritesEntity is a Querydsl query type for FavoritesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoritesEntity extends EntityPathBase<FavoritesEntity> {

    private static final long serialVersionUID = -1201731519L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavoritesEntity favoritesEntity = new QFavoritesEntity("favoritesEntity");

    public final com.kurung.community.entity.QCommunityEntity community;

    public final NumberPath<Integer> favoritesId = createNumber("favoritesId", Integer.class);

    public final com.kurung.diet.entity.QRecipeEntity recipe;

    public final com.kurung.exercise.entity.QRoutinesEntity routines;

    public final com.kurung.stressrelief.entity.QStressReliefEntity stressRelief;

    public final com.kurung.user.entity.QUserEntity user;

    public QFavoritesEntity(String variable) {
        this(FavoritesEntity.class, forVariable(variable), INITS);
    }

    public QFavoritesEntity(Path<? extends FavoritesEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavoritesEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavoritesEntity(PathMetadata metadata, PathInits inits) {
        this(FavoritesEntity.class, metadata, inits);
    }

    public QFavoritesEntity(Class<? extends FavoritesEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.community = inits.isInitialized("community") ? new com.kurung.community.entity.QCommunityEntity(forProperty("community"), inits.get("community")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.kurung.diet.entity.QRecipeEntity(forProperty("recipe"), inits.get("recipe")) : null;
        this.routines = inits.isInitialized("routines") ? new com.kurung.exercise.entity.QRoutinesEntity(forProperty("routines"), inits.get("routines")) : null;
        this.stressRelief = inits.isInitialized("stressRelief") ? new com.kurung.stressrelief.entity.QStressReliefEntity(forProperty("stressRelief")) : null;
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

