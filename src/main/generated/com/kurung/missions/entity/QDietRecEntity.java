package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDietRecEntity is a Querydsl query type for DietRecEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietRecEntity extends EntityPathBase<DietRecEntity> {

    private static final long serialVersionUID = -1130870976L;

    public static final QDietRecEntity dietRecEntity = new QDietRecEntity("dietRecEntity");

    public final NumberPath<Integer> dietRecId = createNumber("dietRecId", Integer.class);

    public final StringPath dietTitle = createString("dietTitle");

    public QDietRecEntity(String variable) {
        super(DietRecEntity.class, forVariable(variable));
    }

    public QDietRecEntity(Path<? extends DietRecEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDietRecEntity(PathMetadata metadata) {
        super(DietRecEntity.class, metadata);
    }

}

