package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDietEntity is a Querydsl query type for DietEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietEntity extends EntityPathBase<DietEntity> {

    private static final long serialVersionUID = 1235482659L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDietEntity dietEntity = new QDietEntity("dietEntity");

    public final DatePath<java.sql.Date> dietDate = createDate("dietDate", java.sql.Date.class);

    public final ListPath<DietFoodEntity, QDietFoodEntity> dietFood = this.<DietFoodEntity, QDietFoodEntity>createList("dietFood", DietFoodEntity.class, QDietFoodEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> dietId = createNumber("dietId", Integer.class);

    public final EnumPath<com.kurung.diet.enumeration.MEAL> meal = createEnum("meal", com.kurung.diet.enumeration.MEAL.class);

    public final QNutritionalEntity nutritional;

    public final com.kurung.user.entity.QUserEntity user;

    public QDietEntity(String variable) {
        this(DietEntity.class, forVariable(variable), INITS);
    }

    public QDietEntity(Path<? extends DietEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDietEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDietEntity(PathMetadata metadata, PathInits inits) {
        this(DietEntity.class, metadata, inits);
    }

    public QDietEntity(Class<? extends DietEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nutritional = inits.isInitialized("nutritional") ? new QNutritionalEntity(forProperty("nutritional"), inits.get("nutritional")) : null;
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

