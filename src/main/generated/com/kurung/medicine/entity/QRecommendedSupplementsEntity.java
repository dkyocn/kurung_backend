package com.kurung.medicine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendedSupplementsEntity is a Querydsl query type for RecommendedSupplementsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendedSupplementsEntity extends EntityPathBase<RecommendedSupplementsEntity> {

    private static final long serialVersionUID = 1650176052L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendedSupplementsEntity recommendedSupplementsEntity = new QRecommendedSupplementsEntity("recommendedSupplementsEntity");

    public final QMedicineInteractionEntity medicineInteraction;

    public final NumberPath<Integer> recSuppId = createNumber("recSuppId", Integer.class);

    public final QSupplementsEntity supplements;

    public QRecommendedSupplementsEntity(String variable) {
        this(RecommendedSupplementsEntity.class, forVariable(variable), INITS);
    }

    public QRecommendedSupplementsEntity(Path<? extends RecommendedSupplementsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendedSupplementsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendedSupplementsEntity(PathMetadata metadata, PathInits inits) {
        this(RecommendedSupplementsEntity.class, metadata, inits);
    }

    public QRecommendedSupplementsEntity(Class<? extends RecommendedSupplementsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.medicineInteraction = inits.isInitialized("medicineInteraction") ? new QMedicineInteractionEntity(forProperty("medicineInteraction"), inits.get("medicineInteraction")) : null;
        this.supplements = inits.isInitialized("supplements") ? new QSupplementsEntity(forProperty("supplements")) : null;
    }

}

