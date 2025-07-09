package com.kurung.medicine.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedicineEntity is a Querydsl query type for MedicineEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMedicineEntity extends EntityPathBase<MedicineEntity> {

    private static final long serialVersionUID = -986600401L;

    public static final QMedicineEntity medicineEntity = new QMedicineEntity("medicineEntity");

    public final StringPath category = createString("category");

    public final StringPath company = createString("company");

    public final ListPath<InputMedicineEntity, QInputMedicineEntity> inputMedicine = this.<InputMedicineEntity, QInputMedicineEntity>createList("inputMedicine", InputMedicineEntity.class, QInputMedicineEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> mediId = createNumber("mediId", Integer.class);

    public final StringPath mediName = createString("mediName");

    public final StringPath mediNameKo = createString("mediNameKo");

    public QMedicineEntity(String variable) {
        super(MedicineEntity.class, forVariable(variable));
    }

    public QMedicineEntity(Path<? extends MedicineEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMedicineEntity(PathMetadata metadata) {
        super(MedicineEntity.class, metadata);
    }

}

