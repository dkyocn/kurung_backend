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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodEntity foodEntity = new QFoodEntity("foodEntity");

    public final ListPath<DietFoodEntity, QDietFoodEntity> dietFood = this.<DietFoodEntity, QDietFoodEntity>createList("dietFood", DietFoodEntity.class, QDietFoodEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> foodId = createNumber("foodId", Integer.class);

    public final ListPath<FoodIngredEntity, QFoodIngredEntity> foodIngred = this.<FoodIngredEntity, QFoodIngredEntity>createList("foodIngred", FoodIngredEntity.class, QFoodIngredEntity.class, PathInits.DIRECT2);

    public final StringPath foodName = createString("foodName");

    public final StringPath foodPhoto = createString("foodPhoto");

    public final QNutritionalEntity nutritional;

    public final ListPath<RecipeEntity, QRecipeEntity> recipe = this.<RecipeEntity, QRecipeEntity>createList("recipe", RecipeEntity.class, QRecipeEntity.class, PathInits.DIRECT2);

    public QFoodEntity(String variable) {
        this(FoodEntity.class, forVariable(variable), INITS);
    }

    public QFoodEntity(Path<? extends FoodEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodEntity(PathMetadata metadata, PathInits inits) {
        this(FoodEntity.class, metadata, inits);
    }

    public QFoodEntity(Class<? extends FoodEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nutritional = inits.isInitialized("nutritional") ? new QNutritionalEntity(forProperty("nutritional"), inits.get("nutritional")) : null;
    }

}

