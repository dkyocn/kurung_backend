package com.kurung.diagnosis.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendedGoalEntity is a Querydsl query type for RecommendedGoalEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendedGoalEntity extends EntityPathBase<RecommendedGoalEntity> {

    private static final long serialVersionUID = -1762776802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendedGoalEntity recommendedGoalEntity = new QRecommendedGoalEntity("recommendedGoalEntity");

    public final NumberPath<Integer> goalId = createNumber("goalId", Integer.class);

    public final StringPath goalText = createString("goalText");

    public final StringPath goalTitle = createString("goalTitle");

    public final QHealthDiagnosisEntity healthDiagnosis;

    public QRecommendedGoalEntity(String variable) {
        this(RecommendedGoalEntity.class, forVariable(variable), INITS);
    }

    public QRecommendedGoalEntity(Path<? extends RecommendedGoalEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendedGoalEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendedGoalEntity(PathMetadata metadata, PathInits inits) {
        this(RecommendedGoalEntity.class, metadata, inits);
    }

    public QRecommendedGoalEntity(Class<? extends RecommendedGoalEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.healthDiagnosis = inits.isInitialized("healthDiagnosis") ? new QHealthDiagnosisEntity(forProperty("healthDiagnosis"), inits.get("healthDiagnosis")) : null;
    }

}

