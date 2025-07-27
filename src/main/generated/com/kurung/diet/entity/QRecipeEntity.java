package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeEntity is a Querydsl query type for RecipeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeEntity extends EntityPathBase<RecipeEntity> {

    private static final long serialVersionUID = -2014049475L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeEntity recipeEntity = new QRecipeEntity("recipeEntity");

    public final QFoodEntity food;

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final StringPath recipeContent = createString("recipeContent");

    public final NumberPath<Integer> recipeId = createNumber("recipeId", Integer.class);

    public QRecipeEntity(String variable) {
        this(RecipeEntity.class, forVariable(variable), INITS);
    }

    public QRecipeEntity(Path<? extends RecipeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeEntity(PathMetadata metadata, PathInits inits) {
        this(RecipeEntity.class, metadata, inits);
    }

    public QRecipeEntity(Class<? extends RecipeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFoodEntity(forProperty("food"), inits.get("food")) : null;
    }

}

