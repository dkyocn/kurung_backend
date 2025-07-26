package com.kurung.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseLogEntity is a Querydsl query type for ExerciseLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseLogEntity extends EntityPathBase<ExerciseLogEntity> {

    private static final long serialVersionUID = 164248767L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseLogEntity exerciseLogEntity = new QExerciseLogEntity("exerciseLogEntity");

    public final StringPath bodyCondition = createString("bodyCondition");

    public final NumberPath<Integer> calories = createNumber("calories", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final QExerciseEntity exercise;

    public final DateTimePath<java.time.LocalDateTime> exerciseDate = createDateTime("exerciseDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> exerciseLogsId = createNumber("exerciseLogsId", Integer.class);

    public final NumberPath<Integer> heartRate = createNumber("heartRate", Integer.class);

    public final StringPath intensity = createString("intensity");

    public final StringPath memo = createString("memo");

    public final StringPath physicalNote = createString("physicalNote");

    public final StringPath postFeeling = createString("postFeeling");

    public final StringPath preCondition = createString("preCondition");

    public final NumberPath<Integer> repCount = createNumber("repCount", Integer.class);

    public final NumberPath<Integer> setCount = createNumber("setCount", Integer.class);

    public final com.kurung.user.entity.QUserEntity user;

    public QExerciseLogEntity(String variable) {
        this(ExerciseLogEntity.class, forVariable(variable), INITS);
    }

    public QExerciseLogEntity(Path<? extends ExerciseLogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseLogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseLogEntity(PathMetadata metadata, PathInits inits) {
        this(ExerciseLogEntity.class, metadata, inits);
    }

    public QExerciseLogEntity(Class<? extends ExerciseLogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exercise = inits.isInitialized("exercise") ? new QExerciseEntity(forProperty("exercise")) : null;
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

