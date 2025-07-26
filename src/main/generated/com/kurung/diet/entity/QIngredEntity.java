package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredEntity is a Querydsl query type for IngredEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredEntity extends EntityPathBase<IngredEntity> {

    private static final long serialVersionUID = -625342882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredEntity ingredEntity = new QIngredEntity("ingredEntity");

    public final ListPath<FoodIngredEntity, QFoodIngredEntity> foodIngred = this.<FoodIngredEntity, QFoodIngredEntity>createList("foodIngred", FoodIngredEntity.class, QFoodIngredEntity.class, PathInits.DIRECT2);

    public final StringPath ingredAllergy = createString("ingredAllergy");

    public final EnumPath<com.kurung.diet.enumeration.INGREDCATEGORY> ingredCategory = createEnum("ingredCategory", com.kurung.diet.enumeration.INGREDCATEGORY.class);

    public final NumberPath<Integer> ingredId = createNumber("ingredId", Integer.class);

    public final StringPath ingredName = createString("ingredName");

    public final QNutritionalEntity nutritional;

    public QIngredEntity(String variable) {
        this(IngredEntity.class, forVariable(variable), INITS);
    }

    public QIngredEntity(Path<? extends IngredEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredEntity(PathMetadata metadata, PathInits inits) {
        this(IngredEntity.class, metadata, inits);
    }

    public QIngredEntity(Class<? extends IngredEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nutritional = inits.isInitialized("nutritional") ? new QNutritionalEntity(forProperty("nutritional"), inits.get("nutritional")) : null;
    }

}

