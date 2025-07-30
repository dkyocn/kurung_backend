package com.kurung.healthReport.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthReportEntity is a Querydsl query type for HealthReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthReportEntity extends EntityPathBase<HealthReportEntity> {

    private static final long serialVersionUID = 1567168155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealthReportEntity healthReportEntity = new QHealthReportEntity("healthReportEntity");

    public final StringPath healthStatus = createString("healthStatus");

    public final NumberPath<Integer> monthlyScore = createNumber("monthlyScore", Integer.class);

    public final NumberPath<Float> progressRate = createNumber("progressRate", Float.class);

    public final NumberPath<Integer> reportId = createNumber("reportId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> reportMonth = createDateTime("reportMonth", java.time.LocalDateTime.class);

    public final StringPath reportPdfPath = createString("reportPdfPath");

    public final com.kurung.user.entity.QUserEntity user;

    public QHealthReportEntity(String variable) {
        this(HealthReportEntity.class, forVariable(variable), INITS);
    }

    public QHealthReportEntity(Path<? extends HealthReportEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealthReportEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealthReportEntity(PathMetadata metadata, PathInits inits) {
        this(HealthReportEntity.class, metadata, inits);
    }

    public QHealthReportEntity(Class<? extends HealthReportEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

