package com.kurung.medicine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedicineInteractionEntity is a Querydsl query type for MedicineInteractionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMedicineInteractionEntity extends EntityPathBase<MedicineInteractionEntity> {

    private static final long serialVersionUID = 63412425L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedicineInteractionEntity medicineInteractionEntity = new QMedicineInteractionEntity("medicineInteractionEntity");

    public final ListPath<InputMedicineEntity, QInputMedicineEntity> inputMedicine = this.<InputMedicineEntity, QInputMedicineEntity>createList("inputMedicine", InputMedicineEntity.class, QInputMedicineEntity.class, PathInits.DIRECT2);

    public final StringPath interaction = createString("interaction");

    public final NumberPath<Integer> mediInterId = createNumber("mediInterId", Integer.class);

    public final ListPath<RecommendedSupplementsEntity, QRecommendedSupplementsEntity> recommendedSupplements = this.<RecommendedSupplementsEntity, QRecommendedSupplementsEntity>createList("recommendedSupplements", RecommendedSupplementsEntity.class, QRecommendedSupplementsEntity.class, PathInits.DIRECT2);

    public final com.kurung.user.entity.QUserEntity user;

    public QMedicineInteractionEntity(String variable) {
        this(MedicineInteractionEntity.class, forVariable(variable), INITS);
    }

    public QMedicineInteractionEntity(Path<? extends MedicineInteractionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedicineInteractionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedicineInteractionEntity(PathMetadata metadata, PathInits inits) {
        this(MedicineInteractionEntity.class, metadata, inits);
    }

    public QMedicineInteractionEntity(Class<? extends MedicineInteractionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

