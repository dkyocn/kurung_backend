package com.kurung.lifeLog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMonthlyHabitMissionsEntity is a Querydsl query type for MonthlyHabitMissionsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonthlyHabitMissionsEntity extends EntityPathBase<MonthlyHabitMissionsEntity> {

    private static final long serialVersionUID = 1280345147L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonthlyHabitMissionsEntity monthlyHabitMissionsEntity = new QMonthlyHabitMissionsEntity("monthlyHabitMissionsEntity");

    public final com.kurung.missions.entity.QHabitRecEntity habitRecId;

    public final DateTimePath<java.time.LocalDateTime> monthlyHabitDate = createDateTime("monthlyHabitDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> monthlyHabitId = createNumber("monthlyHabitId", Integer.class);

    public final com.kurung.user.entity.QUserEntity user;

    public QMonthlyHabitMissionsEntity(String variable) {
        this(MonthlyHabitMissionsEntity.class, forVariable(variable), INITS);
    }

    public QMonthlyHabitMissionsEntity(Path<? extends MonthlyHabitMissionsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMonthlyHabitMissionsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMonthlyHabitMissionsEntity(PathMetadata metadata, PathInits inits) {
        this(MonthlyHabitMissionsEntity.class, metadata, inits);
    }

    public QMonthlyHabitMissionsEntity(Class<? extends MonthlyHabitMissionsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.habitRecId = inits.isInitialized("habitRecId") ? new com.kurung.missions.entity.QHabitRecEntity(forProperty("habitRecId")) : null;
        this.user = inits.isInitialized("user") ? new com.kurung.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

