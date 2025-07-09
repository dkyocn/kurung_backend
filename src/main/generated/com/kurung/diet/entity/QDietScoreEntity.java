package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDietScoreEntity is a Querydsl query type for DietScoreEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietScoreEntity extends EntityPathBase<DietScoreEntity> {

    private static final long serialVersionUID = -1396052779L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDietScoreEntity dietScoreEntity = new QDietScoreEntity("dietScoreEntity");

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final NumberPath<Float> dietScore = createNumber("dietScore", Float.class);

    public final NumberPath<Integer> scoreId = createNumber("scoreId", Integer.class);

    public final com.kurung.user.entity.QUserEntity user;

    public QDietScoreEntity(String variable) {
        this(DietScoreEntity.class, forVariable(variable), INITS);
    }

    public QDietScoreEntity(Path<? extends DietScoreEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDietScoreEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDietScoreEntity(PathMetadata metadata, PathInits inits) {
        this(DietScoreEntity.class, metadata, inits);
    }

    public QDietScoreEntity(Class<? extends DietScoreEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

