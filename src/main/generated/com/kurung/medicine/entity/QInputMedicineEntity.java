package com.kurung.medicine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInputMedicineEntity is a Querydsl query type for InputMedicineEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInputMedicineEntity extends EntityPathBase<InputMedicineEntity> {

    private static final long serialVersionUID = 922110709L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInputMedicineEntity inputMedicineEntity = new QInputMedicineEntity("inputMedicineEntity");

    public final NumberPath<Integer> inputMediId = createNumber("inputMediId", Integer.class);

    public final QMedicineEntity medicine;

    public final QMedicineInteractionEntity medicineInteraction;

    public final StringPath risk = createString("risk");

    public QInputMedicineEntity(String variable) {
        this(InputMedicineEntity.class, forVariable(variable), INITS);
    }

    public QInputMedicineEntity(Path<? extends InputMedicineEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInputMedicineEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInputMedicineEntity(PathMetadata metadata, PathInits inits) {
        this(InputMedicineEntity.class, metadata, inits);
    }

    public QInputMedicineEntity(Class<? extends InputMedicineEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.medicine = inits.isInitialized("medicine") ? new QMedicineEntity(forProperty("medicine")) : null;
        this.medicineInteraction = inits.isInitialized("medicineInteraction") ? new QMedicineInteractionEntity(forProperty("medicineInteraction"), inits.get("medicineInteraction")) : null;
    }

}

