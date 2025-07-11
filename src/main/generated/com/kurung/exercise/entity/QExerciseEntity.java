package com.kurung.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseEntity is a Querydsl query type for ExerciseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseEntity extends EntityPathBase<ExerciseEntity> {

    private static final long serialVersionUID = -1587959189L;

    public static final QExerciseEntity exerciseEntity = new QExerciseEntity("exerciseEntity");

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    public final StringPath exerciseCategory = createString("exerciseCategory");

    public final NumberPath<Integer> exerciseId = createNumber("exerciseId", Integer.class);

    public final StringPath exerciseName = createString("exerciseName");

    public final StringPath tool = createString("tool");

    public QExerciseEntity(String variable) {
        super(ExerciseEntity.class, forVariable(variable));
    }

    public QExerciseEntity(Path<? extends ExerciseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseEntity(PathMetadata metadata) {
        super(ExerciseEntity.class, metadata);
    }

}

