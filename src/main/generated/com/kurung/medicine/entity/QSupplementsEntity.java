package com.kurung.medicine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSupplementsEntity is a Querydsl query type for SupplementsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSupplementsEntity extends EntityPathBase<SupplementsEntity> {

    private static final long serialVersionUID = 2053741739L;

    public static final QSupplementsEntity supplementsEntity = new QSupplementsEntity("supplementsEntity");

    public final StringPath category = createString("category");

    public final StringPath company = createString("company");

    public final ListPath<RecommendedSupplementsEntity, QRecommendedSupplementsEntity> recommendedSupplements = this.<RecommendedSupplementsEntity, QRecommendedSupplementsEntity>createList("recommendedSupplements", RecommendedSupplementsEntity.class, QRecommendedSupplementsEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> suppId = createNumber("suppId", Integer.class);

    public final StringPath suppName = createString("suppName");

    public final StringPath suppNameKo = createString("suppNameKo");

    public QSupplementsEntity(String variable) {
        super(SupplementsEntity.class, forVariable(variable));
    }

    public QSupplementsEntity(Path<? extends SupplementsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSupplementsEntity(PathMetadata metadata) {
        super(SupplementsEntity.class, metadata);
    }

}

