package com.kurung.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1259695407L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final BooleanPath adminYN = createBoolean("adminYN");

    public final ListPath<com.kurung.diet.entity.DietEntity, com.kurung.diet.entity.QDietEntity> diet = this.<com.kurung.diet.entity.DietEntity, com.kurung.diet.entity.QDietEntity>createList("diet", com.kurung.diet.entity.DietEntity.class, com.kurung.diet.entity.QDietEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.diagnosis.entity.HealthAnswerEntity, com.kurung.diagnosis.entity.QHealthAnswerEntity> healthAnswer = this.<com.kurung.diagnosis.entity.HealthAnswerEntity, com.kurung.diagnosis.entity.QHealthAnswerEntity>createList("healthAnswer", com.kurung.diagnosis.entity.HealthAnswerEntity.class, com.kurung.diagnosis.entity.QHealthAnswerEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.diagnosis.entity.HealthDiagnosisEntity, com.kurung.diagnosis.entity.QHealthDiagnosisEntity> healthDiagnosis = this.<com.kurung.diagnosis.entity.HealthDiagnosisEntity, com.kurung.diagnosis.entity.QHealthDiagnosisEntity>createList("healthDiagnosis", com.kurung.diagnosis.entity.HealthDiagnosisEntity.class, com.kurung.diagnosis.entity.QHealthDiagnosisEntity.class, PathInits.DIRECT2);

    public final BooleanPath isActive = createBoolean("isActive");

    public final ListPath<com.kurung.lifeLog.entity.LifeLogEntity, com.kurung.lifeLog.entity.QLifeLogEntity> lifelog = this.<com.kurung.lifeLog.entity.LifeLogEntity, com.kurung.lifeLog.entity.QLifeLogEntity>createList("lifelog", com.kurung.lifeLog.entity.LifeLogEntity.class, com.kurung.lifeLog.entity.QLifeLogEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.lifeLog.entity.MonthlyLifeLogEntity, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity> monthlyLifelog = this.<com.kurung.lifeLog.entity.MonthlyLifeLogEntity, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity>createList("monthlyLifelog", com.kurung.lifeLog.entity.MonthlyLifeLogEntity.class, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity.class, PathInits.DIRECT2);

    public final StringPath profileImg = createString("profileImg");

    public final DatePath<java.sql.Date> userAge = createDate("userAge", java.sql.Date.class);

    public final StringPath userFaceLoginRef = createString("userFaceLoginRef");

    public final BooleanPath userFaceLoginYN = createBoolean("userFaceLoginYN");

    public final EnumPath<com.kurung.user.enumeration.Gender> userGender = createEnum("userGender", com.kurung.user.enumeration.Gender.class);

    public final StringPath userId = createString("userId");

    public final StringPath userKey = createString("userKey");

    public final StringPath userNick = createString("userNick");

    public final EnumPath<com.kurung.user.enumeration.UserPath> userPath = createEnum("userPath", com.kurung.user.enumeration.UserPath.class);

    public final StringPath userPwd = createString("userPwd");

    public final StringPath userUuid = createString("userUuid");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

