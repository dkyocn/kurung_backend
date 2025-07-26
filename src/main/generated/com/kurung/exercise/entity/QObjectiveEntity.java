package com.kurung.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObjectiveEntity is a Querydsl query type for ObjectiveEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QObjectiveEntity extends EntityPathBase<ObjectiveEntity> {

    private static final long serialVersionUID = -656887316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObjectiveEntity objectiveEntity = new QObjectiveEntity("objectiveEntity");

    public final com.kurung.common.entity.QBaseEntity _super = new com.kurung.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> objectiveCount = createNumber("objectiveCount", Integer.class);

    public final NumberPath<Integer> objectiveDuration = createNumber("objectiveDuration", Integer.class);

    public final NumberPath<Integer> objectiveId = createNumber("objectiveId", Integer.class);

    public final StringPath objectiveTitle = createString("objectiveTitle");

    public final NumberPath<Float> objectiveWeight = createNumber("objectiveWeight", Float.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.kurung.user.entity.QUserEntity user;

    public QObjectiveEntity(String variable) {
        this(ObjectiveEntity.class, forVariable(variable), INITS);
    }

    public QObjectiveEntity(Path<? extends ObjectiveEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QObjectiveEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QObjectiveEntity(PathMetadata metadata, PathInits inits) {
        this(ObjectiveEntity.class, metadata, inits);
    }

    public QObjectiveEntity(Class<? extends ObjectiveEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

