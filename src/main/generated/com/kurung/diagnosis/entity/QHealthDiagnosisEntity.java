package com.kurung.diagnosis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHealthDiagnosisEntity is a Querydsl query type for HealthDiagnosisEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthDiagnosisEntity extends EntityPathBase<HealthDiagnosisEntity> {

    private static final long serialVersionUID = 1086448581L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealthDiagnosisEntity healthDiagnosisEntity = new QHealthDiagnosisEntity("healthDiagnosisEntity");

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    public final StringPath diagnosisSummary = createString("diagnosisSummary");

    public final NumberPath<Integer> healthId = createNumber("healthId", Integer.class);

    public final ListPath<RecommendedGoalEntity, QRecommendedGoalEntity> recommendedGoal = this.<RecommendedGoalEntity, QRecommendedGoalEntity>createList("recommendedGoal", RecommendedGoalEntity.class, QRecommendedGoalEntity.class, PathInits.DIRECT2);

    public final StringPath reportPdfPath = createString("reportPdfPath");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final com.kurung.user.entity.QUserEntity user;

    public QHealthDiagnosisEntity(String variable) {
        this(HealthDiagnosisEntity.class, forVariable(variable), INITS);
    }

    public QHealthDiagnosisEntity(Path<? extends HealthDiagnosisEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHealthDiagnosisEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHealthDiagnosisEntity(PathMetadata metadata, PathInits inits) {
        this(HealthDiagnosisEntity.class, metadata, inits);
    }

    public QHealthDiagnosisEntity(Class<? extends HealthDiagnosisEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

