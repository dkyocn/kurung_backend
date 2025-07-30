package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseRecEntity is a Querydsl query type for ExerciseRecEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseRecEntity extends EntityPathBase<ExerciseRecEntity> {

    private static final long serialVersionUID = 1382328668L;

    public static final QExerciseRecEntity exerciseRecEntity = new QExerciseRecEntity("exerciseRecEntity");

    public final NumberPath<Integer> exerciseRecId = createNumber("exerciseRecId", Integer.class);

    public final StringPath exerciseTitle = createString("exerciseTitle");

    public QExerciseRecEntity(String variable) {
        super(ExerciseRecEntity.class, forVariable(variable));
    }

    public QExerciseRecEntity(Path<? extends ExerciseRecEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseRecEntity(PathMetadata metadata) {
        super(ExerciseRecEntity.class, metadata);
    }

}

