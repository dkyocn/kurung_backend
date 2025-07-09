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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final BooleanPath adminYN = createBoolean("adminYN");

    public final ListPath<com.kurung.chatbot.entity.ChatbotEntity, com.kurung.chatbot.entity.QChatbotEntity> chatbotList = this.<com.kurung.chatbot.entity.ChatbotEntity, com.kurung.chatbot.entity.QChatbotEntity>createList("chatbotList", com.kurung.chatbot.entity.ChatbotEntity.class, com.kurung.chatbot.entity.QChatbotEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.community.entity.CommentEntity, com.kurung.community.entity.QCommentEntity> comment = this.<com.kurung.community.entity.CommentEntity, com.kurung.community.entity.QCommentEntity>createList("comment", com.kurung.community.entity.CommentEntity.class, com.kurung.community.entity.QCommentEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.community.entity.CommunityEntity, com.kurung.community.entity.QCommunityEntity> community = this.<com.kurung.community.entity.CommunityEntity, com.kurung.community.entity.QCommunityEntity>createList("community", com.kurung.community.entity.CommunityEntity.class, com.kurung.community.entity.QCommunityEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.diet.entity.DietEntity, com.kurung.diet.entity.QDietEntity> diet = this.<com.kurung.diet.entity.DietEntity, com.kurung.diet.entity.QDietEntity>createList("diet", com.kurung.diet.entity.DietEntity.class, com.kurung.diet.entity.QDietEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.exercise.entity.ExerciseLogEntity, com.kurung.exercise.entity.QExerciseLogEntity> exerciseLogs = this.<com.kurung.exercise.entity.ExerciseLogEntity, com.kurung.exercise.entity.QExerciseLogEntity>createList("exerciseLogs", com.kurung.exercise.entity.ExerciseLogEntity.class, com.kurung.exercise.entity.QExerciseLogEntity.class, PathInits.DIRECT2);

    public final BooleanPath isActive = createBoolean("isActive");

    public final com.kurung.exercise.entity.QObjectiveEntity Objective;
  
    public final ListPath<com.kurung.diet.entity.DietScoreEntity, com.kurung.diet.entity.QDietScoreEntity> dietScore = this.<com.kurung.diet.entity.DietScoreEntity, com.kurung.diet.entity.QDietScoreEntity>createList("dietScore", com.kurung.diet.entity.DietScoreEntity.class, com.kurung.diet.entity.QDietScoreEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.favorites.entity.FavoritesEntity, com.kurung.favorites.entity.QFavoritesEntity> favorites = this.<com.kurung.favorites.entity.FavoritesEntity, com.kurung.favorites.entity.QFavoritesEntity>createList("favorites", com.kurung.favorites.entity.FavoritesEntity.class, com.kurung.favorites.entity.QFavoritesEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.diagnosis.entity.HealthAnswerEntity, com.kurung.diagnosis.entity.QHealthAnswerEntity> healthAnswer = this.<com.kurung.diagnosis.entity.HealthAnswerEntity, com.kurung.diagnosis.entity.QHealthAnswerEntity>createList("healthAnswer", com.kurung.diagnosis.entity.HealthAnswerEntity.class, com.kurung.diagnosis.entity.QHealthAnswerEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.diagnosis.entity.HealthDiagnosisEntity, com.kurung.diagnosis.entity.QHealthDiagnosisEntity> healthDiagnosis = this.<com.kurung.diagnosis.entity.HealthDiagnosisEntity, com.kurung.diagnosis.entity.QHealthDiagnosisEntity>createList("healthDiagnosis", com.kurung.diagnosis.entity.HealthDiagnosisEntity.class, com.kurung.diagnosis.entity.QHealthDiagnosisEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.healthReport.entity.HealthReportEntity, com.kurung.healthReport.entity.QHealthReportEntity> healthReport = this.<com.kurung.healthReport.entity.HealthReportEntity, com.kurung.healthReport.entity.QHealthReportEntity>createList("healthReport", com.kurung.healthReport.entity.HealthReportEntity.class, com.kurung.healthReport.entity.QHealthReportEntity.class, PathInits.DIRECT2);

    public final BooleanPath isActive = createBoolean("isActive");

    public final ListPath<com.kurung.lifeLog.entity.LifeLogEntity, com.kurung.lifeLog.entity.QLifeLogEntity> lifelog = this.<com.kurung.lifeLog.entity.LifeLogEntity, com.kurung.lifeLog.entity.QLifeLogEntity>createList("lifelog", com.kurung.lifeLog.entity.LifeLogEntity.class, com.kurung.lifeLog.entity.QLifeLogEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.medicine.entity.MedicineInteractionEntity, com.kurung.medicine.entity.QMedicineInteractionEntity> medicineInteraction = this.<com.kurung.medicine.entity.MedicineInteractionEntity, com.kurung.medicine.entity.QMedicineInteractionEntity>createList("medicineInteraction", com.kurung.medicine.entity.MedicineInteractionEntity.class, com.kurung.medicine.entity.QMedicineInteractionEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.missions.entity.MissionsEntity, com.kurung.missions.entity.QMissionsEntity> missions = this.<com.kurung.missions.entity.MissionsEntity, com.kurung.missions.entity.QMissionsEntity>createList("missions", com.kurung.missions.entity.MissionsEntity.class, com.kurung.missions.entity.QMissionsEntity.class, PathInits.DIRECT2);

    public final ListPath<com.kurung.lifeLog.entity.MonthlyLifeLogEntity, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity> monthlyLifelog = this.<com.kurung.lifeLog.entity.MonthlyLifeLogEntity, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity>createList("monthlyLifelog", com.kurung.lifeLog.entity.MonthlyLifeLogEntity.class, com.kurung.lifeLog.entity.QMonthlyLifeLogEntity.class, PathInits.DIRECT2);

    public final StringPath profileImg = createString("profileImg");

    public final ListPath<com.kurung.exercise.entity.RoutinesEntity, com.kurung.exercise.entity.QRoutinesEntity> Routine = this.<com.kurung.exercise.entity.RoutinesEntity, com.kurung.exercise.entity.QRoutinesEntity>createList("Routine", com.kurung.exercise.entity.RoutinesEntity.class, com.kurung.exercise.entity.QRoutinesEntity.class, PathInits.DIRECT2);

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
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.Objective = inits.isInitialized("Objective") ? new com.kurung.exercise.entity.QObjectiveEntity(forProperty("Objective"), inits.get("Objective")) : null;
    }

}

