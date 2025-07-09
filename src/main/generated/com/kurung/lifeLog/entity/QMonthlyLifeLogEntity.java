package com.kurung.lifeLog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMonthlyLifeLogEntity is a Querydsl query type for MonthlyLifeLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonthlyLifeLogEntity extends EntityPathBase<MonthlyLifeLogEntity> {

    private static final long serialVersionUID = 461846760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonthlyLifeLogEntity monthlyLifeLogEntity = new QMonthlyLifeLogEntity("monthlyLifeLogEntity");

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> monthlyLifeLogId = createNumber("monthlyLifeLogId", Integer.class);

    public final StringPath monthlySummary = createString("monthlySummary");

    public final com.kurung.user.entity.QUserEntity user;

    public QMonthlyLifeLogEntity(String variable) {
        this(MonthlyLifeLogEntity.class, forVariable(variable), INITS);
    }

    public QMonthlyLifeLogEntity(Path<? extends MonthlyLifeLogEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMonthlyLifeLogEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMonthlyLifeLogEntity(PathMetadata metadata, PathInits inits) {
        this(MonthlyLifeLogEntity.class, metadata, inits);
    }

    public QMonthlyLifeLogEntity(Class<? extends MonthlyLifeLogEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

