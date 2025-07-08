package com.kurung.missions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMissionsEntity is a Querydsl query type for MissionsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionsEntity extends EntityPathBase<MissionsEntity> {

    private static final long serialVersionUID = 219790089L;

    public static final QMissionsEntity missionsEntity = new QMissionsEntity("missionsEntity");

    public final StringPath displayType = createString("displayType");

    public final BooleanPath isComplete = createBoolean("isComplete");

    public final NumberPath<Integer> missionId = createNumber("missionId", Integer.class);

    public final DatePath<java.sql.Date> startedDate = createDate("startedDate", java.sql.Date.class);

    public final BooleanPath toggleOption = createBoolean("toggleOption");

    public final StringPath userUuid = createString("userUuid");

    public QMissionsEntity(String variable) {
        super(MissionsEntity.class, forVariable(variable));
    }

    public QMissionsEntity(Path<? extends MissionsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMissionsEntity(PathMetadata metadata) {
        super(MissionsEntity.class, metadata);
    }

}

