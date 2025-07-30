package com.kurung.healthinfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthInfoEntity is a Querydsl query type for HealthInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthInfoEntity extends EntityPathBase<HealthInfoEntity> {

    private static final long serialVersionUID = -432397649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealthInfoEntity healthInfoEntity = new QHealthInfoEntity("healthInfoEntity");

    public final com.kurung.common.entity.QBaseEntity _super = new com.kurung.common.entity.QBaseEntity(this);

    public final NumberPath<Float> bmi = createNumber("bmi", Float.class);

    public final NumberPath<Float> bodyfatpercent = createNumber("bodyfatpercent", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> healthinfoId = createNumber("healthinfoId", Integer.class);

    public final NumberPath<Float> height = createNumber("height", Float.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Float> musclemass = createNumber("musclemass", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.kurung.user.entity.QUserEntity user;

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QHealthInfoEntity(String variable) {
        this(HealthInfoEntity.class, forVariable(variable), INITS);
    }

    public QHealthInfoEntity(Path<? extends HealthInfoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealthInfoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealthInfoEntity(PathMetadata metadata, PathInits inits) {
        this(HealthInfoEntity.class, metadata, inits);
    }

    public QHealthInfoEntity(Class<? extends HealthInfoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

