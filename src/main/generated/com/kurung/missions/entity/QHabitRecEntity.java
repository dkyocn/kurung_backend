package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHabitRecEntity is a Querydsl query type for HabitRecEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHabitRecEntity extends EntityPathBase<HabitRecEntity> {

    private static final long serialVersionUID = -1710579362L;

    public static final QHabitRecEntity habitRecEntity = new QHabitRecEntity("habitRecEntity");

    public final NumberPath<Integer> habitRecId = createNumber("habitRecId", Integer.class);

    public final StringPath habitTitle = createString("habitTitle");

    public QHabitRecEntity(String variable) {
        super(HabitRecEntity.class, forVariable(variable));
    }

    public QHabitRecEntity(Path<? extends HabitRecEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHabitRecEntity(PathMetadata metadata) {
        super(HabitRecEntity.class, metadata);
    }

}

