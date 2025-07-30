package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodAllergyEntity is a Querydsl query type for FoodAllergyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodAllergyEntity extends EntityPathBase<FoodAllergyEntity> {

    private static final long serialVersionUID = -1804684519L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodAllergyEntity foodAllergyEntity = new QFoodAllergyEntity("foodAllergyEntity");

    public final QAllergyEntity allergy;

    public final QFoodEntity food;

    public final NumberPath<Integer> foodAllergyId = createNumber("foodAllergyId", Integer.class);

    public QFoodAllergyEntity(String variable) {
        this(FoodAllergyEntity.class, forVariable(variable), INITS);
    }

    public QFoodAllergyEntity(Path<? extends FoodAllergyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodAllergyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodAllergyEntity(PathMetadata metadata, PathInits inits) {
        this(FoodAllergyEntity.class, metadata, inits);
    }

    public QFoodAllergyEntity(Class<? extends FoodAllergyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.allergy = inits.isInitialized("allergy") ? new QAllergyEntity(forProperty("allergy")) : null;
        this.food = inits.isInitialized("food") ? new QFoodEntity(forProperty("food"), inits.get("food")) : null;
    }

}

