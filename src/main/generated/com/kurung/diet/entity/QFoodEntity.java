package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodEntity is a Querydsl query type for FoodEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodEntity extends EntityPathBase<FoodEntity> {

    private static final long serialVersionUID = 1815707917L;

    public static final QFoodEntity foodEntity = new QFoodEntity("foodEntity");

    public final ListPath<DietFoodEntity, QDietFoodEntity> dietFood = this.<DietFoodEntity, QDietFoodEntity>createList("dietFood", DietFoodEntity.class, QDietFoodEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> foodId = createNumber("foodId", Integer.class);

    public final StringPath foodName = createString("foodName");

    public final StringPath foodPhoto = createString("foodPhoto");

    public QFoodEntity(String variable) {
        super(FoodEntity.class, forVariable(variable));
    }

    public QFoodEntity(Path<? extends FoodEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodEntity(PathMetadata metadata) {
        super(FoodEntity.class, metadata);
    }

}

