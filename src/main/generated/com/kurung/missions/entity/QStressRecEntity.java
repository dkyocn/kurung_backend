package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStressRecEntity is a Querydsl query type for StressRecEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStressRecEntity extends EntityPathBase<StressRecEntity> {

    private static final long serialVersionUID = -1265865408L;

    public static final QStressRecEntity stressRecEntity = new QStressRecEntity("stressRecEntity");

    public final NumberPath<Integer> stressRecId = createNumber("stressRecId", Integer.class);

    public final StringPath stressTitle = createString("stressTitle");

    public QStressRecEntity(String variable) {
        super(StressRecEntity.class, forVariable(variable));
    }

    public QStressRecEntity(Path<? extends StressRecEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStressRecEntity(PathMetadata metadata) {
        super(StressRecEntity.class, metadata);
    }

}

