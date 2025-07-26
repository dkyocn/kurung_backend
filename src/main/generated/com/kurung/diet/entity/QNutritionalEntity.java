package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNutritionalEntity is a Querydsl query type for NutritionalEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNutritionalEntity extends EntityPathBase<NutritionalEntity> {

    private static final long serialVersionUID = 1556063962L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNutritionalEntity nutritionalEntity = new QNutritionalEntity("nutritionalEntity");

    public final NumberPath<Integer> carb = createNumber("carb", Integer.class);

    public final NumberPath<Integer> cholesterol = createNumber("cholesterol", Integer.class);

    public final QDietEntity diet;

    public final QFoodEntity food;

    public final QIngredEntity ingred;

    public final NumberPath<Integer> kcal = createNumber("kcal", Integer.class);

    public final NumberPath<Integer> nutritionalId = createNumber("nutritionalId", Integer.class);

    public final NumberPath<Integer> protein = createNumber("protein", Integer.class);

    public final NumberPath<Integer> saturatedFat = createNumber("saturatedFat", Integer.class);

    public final NumberPath<Integer> sodium = createNumber("sodium", Integer.class);

    public final NumberPath<Integer> sugar = createNumber("sugar", Integer.class);

    public final NumberPath<Integer> transFat = createNumber("transFat", Integer.class);

    public QNutritionalEntity(String variable) {
        this(NutritionalEntity.class, forVariable(variable), INITS);
    }

    public QNutritionalEntity(Path<? extends NutritionalEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNutritionalEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNutritionalEntity(PathMetadata metadata, PathInits inits) {
        this(NutritionalEntity.class, metadata, inits);
    }

    public QNutritionalEntity(Class<? extends NutritionalEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diet = inits.isInitialized("diet") ? new QDietEntity(forProperty("diet"), inits.get("diet")) : null;
        this.food = inits.isInitialized("food") ? new QFoodEntity(forProperty("food"), inits.get("food")) : null;
        this.ingred = inits.isInitialized("ingred") ? new QIngredEntity(forProperty("ingred"), inits.get("ingred")) : null;
    }

}

