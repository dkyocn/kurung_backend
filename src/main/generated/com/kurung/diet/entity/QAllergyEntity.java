package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAllergyEntity is a Querydsl query type for AllergyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllergyEntity extends EntityPathBase<AllergyEntity> {

    private static final long serialVersionUID = -1318924073L;

    public static final QAllergyEntity allergyEntity = new QAllergyEntity("allergyEntity");

    public final NumberPath<Integer> allergyId = createNumber("allergyId", Integer.class);

    public final StringPath allergyName = createString("allergyName");

    public final ListPath<FoodAllergyEntity, QFoodAllergyEntity> foodAllergy = this.<FoodAllergyEntity, QFoodAllergyEntity>createList("foodAllergy", FoodAllergyEntity.class, QFoodAllergyEntity.class, PathInits.DIRECT2);

    public QAllergyEntity(String variable) {
        super(AllergyEntity.class, forVariable(variable));
    }

    public QAllergyEntity(Path<? extends AllergyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllergyEntity(PathMetadata metadata) {
        super(AllergyEntity.class, metadata);
    }

}

