package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodIngredEntity is a Querydsl query type for FoodIngredEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodIngredEntity extends EntityPathBase<FoodIngredEntity> {

    private static final long serialVersionUID = 1298650076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodIngredEntity foodIngredEntity = new QFoodIngredEntity("foodIngredEntity");

    public final QFoodEntity food;

    public final NumberPath<Integer> foodIngredId = createNumber("foodIngredId", Integer.class);

    public final QIngredEntity ingred;

    public QFoodIngredEntity(String variable) {
        this(FoodIngredEntity.class, forVariable(variable), INITS);
    }

    public QFoodIngredEntity(Path<? extends FoodIngredEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodIngredEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodIngredEntity(PathMetadata metadata, PathInits inits) {
        this(FoodIngredEntity.class, metadata, inits);
    }

    public QFoodIngredEntity(Class<? extends FoodIngredEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFoodEntity(forProperty("food"), inits.get("food")) : null;
        this.ingred = inits.isInitialized("ingred") ? new QIngredEntity(forProperty("ingred"), inits.get("ingred")) : null;
    }

}

