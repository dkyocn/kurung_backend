package com.kurung.user.entity;

import com.kurung.common.config.BooleanToInteger;
import com.kurung.community.entity.CommentEntity;
import com.kurung.community.entity.CommunityEntity;
import com.kurung.diagnosis.entity.HealthAnswerEntity;
import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.diet.entity.DietEntity;
import com.kurung.medicine.entity.MedicineInteractionEntity;
import com.kurung.healthReport.entity.HealthReportEntity;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_USER")
public class UserEntity {

    @Id
    @Column(name = "USER_UUID")
    private String userUuid;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_FACELOGIN_YN")
    @Convert(converter = BooleanToInteger.class)
    private boolean userFaceLoginYN;
    @Column(name = "USER_FACELOGIN_REF", length = 1000)
    private String userFaceLoginRef;
    @Column(name = "USER_PWD")
    private String userPwd;
    @Column(name = "USER_NICK", length = 20)
    private String userNick;
    @Column(name = "USER_GENDER", length = 10)
    @Enumerated(EnumType.STRING)
    private Gender userGender;
    @Column(name = "USER_AGE")
    private Date userAge;
    @Column(name = "USER_KEY")
    private String userKey;
    @Column(name = "USER_PATH")
    @Enumerated(EnumType.STRING)
    private UserPath userPath;
    @Column(name = "PROFILE_IMG", length = 200)
    private String profileImg;
    @Column(name = "IS_ACTIVE")
    @Convert(converter = BooleanToInteger.class)
    private boolean isActive;
    @Column(name = "ADMIN_YN")
    @Convert(converter = BooleanToInteger.class)
    private boolean adminYN;
    @ToString.Exclude // ToString 메서드에서 해당 필드 제외 (무한루프방지)
    // @OneToMany : 일대다 매핑
    // mappedBy diet:user 에서 엔티티 관계 소유자 지정
    // FetchType : 로딩 방법
    // LAZY : 지연로딩, EAGER : 즉시 로딩
    // 차이점 즉시로딩은 해당 entity 조회 시 관련된 entity 전부 즉시 조회하여 가져옴, 지연로딩은 해당 entity의 포함된 entity를 조회할 때 조회하여 데이터 가져옴
    // orphanRemoval : 연관관계가 사라지면 즉, 고아 entity 삭제
    // cascade : 모든 cascade 적용
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DietEntity> diet;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommunityEntity> community;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentEntity> comment;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MedicineInteractionEntity> medicineInteraction;
    private List<LifeLogEntity> lifelog;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MonthlyLifeLogEntity> monthlyLifelog;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HealthDiagnosisEntity> healthDiagnosis;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HealthAnswerEntity> healthAnswer;
    private List<HealthReportEntity> healthReport;
}
