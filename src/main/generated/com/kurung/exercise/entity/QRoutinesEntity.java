package com.kurung.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoutinesEntity is a Querydsl query type for RoutinesEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoutinesEntity extends EntityPathBase<RoutinesEntity> {

    private static final long serialVersionUID = 825951106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoutinesEntity routinesEntity = new QRoutinesEntity("routinesEntity");

    public final StringPath place = createString("place");

    public final StringPath routineLevel = createString("routineLevel");

    public final NumberPath<Integer> routinesId = createNumber("routinesId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> savedDate = createDateTime("savedDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final com.kurung.user.entity.QUserEntity user;

    public final StringPath videoUrl = createString("videoUrl");

    public QRoutinesEntity(String variable) {
        this(RoutinesEntity.class, forVariable(variable), INITS);
    }

    public QRoutinesEntity(Path<? extends RoutinesEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoutinesEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoutinesEntity(PathMetadata metadata, PathInits inits) {
        this(RoutinesEntity.class, metadata, inits);
    }

    public QRoutinesEntity(Class<? extends RoutinesEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

