package com.kurung.diet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDietFoodEntity is a Querydsl query type for DietFoodEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietFoodEntity extends EntityPathBase<DietFoodEntity> {

    private static final long serialVersionUID = -1864003039L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDietFoodEntity dietFoodEntity = new QDietFoodEntity("dietFoodEntity");

    public final QDietEntity diet;

    public final NumberPath<Integer> dietFoodId = createNumber("dietFoodId", Integer.class);

    public final QFoodEntity food;

    public QDietFoodEntity(String variable) {
        this(DietFoodEntity.class, forVariable(variable), INITS);
    }

    public QDietFoodEntity(Path<? extends DietFoodEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDietFoodEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDietFoodEntity(PathMetadata metadata, PathInits inits) {
        this(DietFoodEntity.class, metadata, inits);
    }

    public QDietFoodEntity(Class<? extends DietFoodEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diet = inits.isInitialized("diet") ? new QDietEntity(forProperty("diet"), inits.get("diet")) : null;
        this.food = inits.isInitialized("food") ? new QFoodEntity(forProperty("food")) : null;
    }

}

