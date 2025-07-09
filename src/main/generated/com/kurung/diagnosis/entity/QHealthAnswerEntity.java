package com.kurung.diagnosis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthAnswerEntity is a Querydsl query type for HealthAnswerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthAnswerEntity extends EntityPathBase<HealthAnswerEntity> {

    private static final long serialVersionUID = -1183155888L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealthAnswerEntity healthAnswerEntity = new QHealthAnswerEntity("healthAnswerEntity");

    public final NumberPath<Integer> answerId = createNumber("answerId", Integer.class);

    public final QHealthOptionEntity option;

    public final StringPath textAnswer = createString("textAnswer");

    public final com.kurung.user.entity.QUserEntity user;

    public QHealthAnswerEntity(String variable) {
        this(HealthAnswerEntity.class, forVariable(variable), INITS);
    }

    public QHealthAnswerEntity(Path<? extends HealthAnswerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealthAnswerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealthAnswerEntity(PathMetadata metadata, PathInits inits) {
        this(HealthAnswerEntity.class, metadata, inits);
    }

    public QHealthAnswerEntity(Class<? extends HealthAnswerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option = inits.isInitialized("option") ? new QHealthOptionEntity(forProperty("option"), inits.get("option")) : null;
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

