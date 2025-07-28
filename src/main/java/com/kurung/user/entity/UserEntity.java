package com.kurung.user.entity;

import com.kurung.chatbot.entity.ChatbotEntity;
import com.kurung.common.config.BooleanToInteger;
import com.kurung.community.entity.CommentEntity;
import com.kurung.community.entity.CommunityEntity;
import com.kurung.diagnosis.entity.HealthAnswerEntity;
import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.diet.entity.DietEntity;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.favorites.entity.FavoritesEntity;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import com.kurung.medicine.entity.MedicineInteractionEntity;
import com.kurung.healthReport.entity.HealthReportEntity;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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
  private LocalDateTime userAge;

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

  @Column(name = "USER_REFRESH_TOKEN")
  private String userRefreshToken;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<DietEntity> diet;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ExerciseLogEntity> exerciseLogs;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ObjectiveEntity> ObjectiveList;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<RoutinesEntity> Routine;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<MissionsEntity> missions;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<FavoritesEntity> favorites;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<ChatbotEntity> chatbotList;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<DietScoreEntity> dietScore;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CommunityEntity> community;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CommentEntity> comment;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<MedicineInteractionEntity> medicineInteraction;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<LifeLogEntity> lifelog;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<MonthlyLifeLogEntity> monthlyLifelog;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<HealthDiagnosisEntity> healthDiagnosis;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<HealthAnswerEntity> healthAnswer;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<HealthReportEntity> healthReport;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<MonthlyHabitMissionsEntity> monthlyHabitMissions;

  @Builder(builderMethodName = "createUserBuilder", builderClassName = "createUserBuilder")
  public UserEntity(UserDTO userDTO) {
    this.userUuid = userDTO.getUserUuid();
    this.userId = userDTO.getUserId();
    this.userFaceLoginYN = userDTO.isUserFaceLoginYN();
    this.userFaceLoginRef = userDTO.getUserFaceLoginRef();
    this.userPwd = userDTO.getUserPwd();
    this.userNick = userDTO.getUserNick();
    this.userGender = userDTO.getUserGender();
    this.userAge = userDTO.getUserAge();
    this.userKey = userDTO.getUserKey() != null ? userDTO.getUserKey() : null;
    this.userPath = userDTO.getUserPath();
    this.profileImg = userDTO.getProfileImg() != null ? userDTO.getProfileImg() : null;
    this.adminYN = userDTO.isAdminYN();
    this.isActive = userDTO.isActive();
  }

  // update용 메서드 추가
  public void updateUserDTO(UserDTO userDTO) {
    this.userPwd = userDTO.getUserPwd();
    this.userNick = userDTO.getUserNick();
    this.userGender = userDTO.getUserGender();
    this.userAge = userDTO.getUserAge();
    this.profileImg = userDTO.getProfileImg();
  }

  public void updateRefresh(String refreshToken){
    this.userRefreshToken = refreshToken;
  }

  public void updatePassword(String newPassword) {
    this.userPwd = newPassword;
  }
 
  public void assignUserUuid(String uuid) {
    this.userUuid = uuid;
  }
}
