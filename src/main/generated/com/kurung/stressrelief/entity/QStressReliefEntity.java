package com.kurung.stressrelief.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStressReliefEntity is a Querydsl query type for StressReliefEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStressReliefEntity extends EntityPathBase<StressReliefEntity> {

    private static final long serialVersionUID = 243586533L;

    public static final QStressReliefEntity stressReliefEntity = new QStressReliefEntity("stressReliefEntity");

    public final StringPath stressRelief = createString("stressRelief");

    public final StringPath stressReliefEffect = createString("stressReliefEffect");

    public final NumberPath<Integer> stressReliefId = createNumber("stressReliefId", Integer.class);

    public final StringPath stressReliefTitle = createString("stressReliefTitle");

    public QStressReliefEntity(String variable) {
        super(StressReliefEntity.class, forVariable(variable));
    }

    public QStressReliefEntity(Path<? extends StressReliefEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStressReliefEntity(PathMetadata metadata) {
        super(StressReliefEntity.class, metadata);
    }

}

