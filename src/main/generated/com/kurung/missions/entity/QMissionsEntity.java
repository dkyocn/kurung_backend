package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionsEntity is a Querydsl query type for MissionsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionsEntity extends EntityPathBase<MissionsEntity> {

    private static final long serialVersionUID = 219790089L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionsEntity missionsEntity = new QMissionsEntity("missionsEntity");

    public final EnumPath<com.kurung.common.enumeration.HealthType> displayType = createEnum("displayType", com.kurung.common.enumeration.HealthType.class);

    public final BooleanPath isComplete = createBoolean("isComplete");

    public final NumberPath<Integer> missionId = createNumber("missionId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startedDate = createDateTime("startedDate", java.time.LocalDateTime.class);

    public final BooleanPath toggleOption = createBoolean("toggleOption");

    public final com.kurung.user.entity.QUserEntity user;

    public QMissionsEntity(String variable) {
        this(MissionsEntity.class, forVariable(variable), INITS);
    }

    public QMissionsEntity(Path<? extends MissionsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionsEntity(PathMetadata metadata, PathInits inits) {
        this(MissionsEntity.class, metadata, inits);
    }

    public QMissionsEntity(Class<? extends MissionsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

