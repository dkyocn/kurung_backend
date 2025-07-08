package com.kurung.lifeLog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLifeLogEntity is a Querydsl query type for LifeLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLifeLogEntity extends EntityPathBase<LifeLogEntity> {

    private static final long serialVersionUID = 476109985L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLifeLogEntity lifeLogEntity = new QLifeLogEntity("lifeLogEntity");

    public final StringPath activity = createString("activity");

    public final DatePath<java.sql.Date> bedTime = createDate("bedTime", java.sql.Date.class);

    public final StringPath emotion = createString("emotion");

    public final StringPath emotionWrite = createString("emotionWrite");

    public final NumberPath<Integer> lifelogId = createNumber("lifelogId", Integer.class);

    public final StringPath llImagePath = createString("llImagePath");

    public final StringPath llPdfPath = createString("llPdfPath");

    public final StringPath memo = createString("memo");

    public final com.kurung.user.entity.QUserEntity user;

    public final DatePath<java.sql.Date> wakeupTime = createDate("wakeupTime", java.sql.Date.class);

    public QLifeLogEntity(String variable) {
        this(LifeLogEntity.class, forVariable(variable), INITS);
    }

    public QLifeLogEntity(Path<? extends LifeLogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLifeLogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLifeLogEntity(PathMetadata metadata, PathInits inits) {
        this(LifeLogEntity.class, metadata, inits);
    }

    public QLifeLogEntity(Class<? extends LifeLogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

