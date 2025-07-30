package com.kurung.lifeLog.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_LIFELOG")
public class LifeLogEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LIFELOG_ID")
  private int lifelogId;
  @Column(name = "LIFELOG_DATE")
  private LocalDateTime lifelogDate;
  @Column(name = "EMOTION", length = 50, nullable = false)
  private String emotion;
  @Column(name = "EMOTION_WRITE", length = 300, nullable = false)
  private String emotionWrite;
  @Column(name = "BED_TIME", nullable = false)
  private LocalDateTime bedTime;
  @Column(name = "WAKEUP_TIME", nullable = false)
  private LocalDateTime wakeupTime;
  @Column(name = "ACTIVITY", length = 50, nullable = false)
  private String activity;
  @Column(name = "MEMO", nullable = false)
  private String memo;
  @Column(name = "LL_PDF_PATH", length = 200, nullable = false)
  private String llPdfPath;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

  public void updateLifeLog(LifeLogDTO lifeLogDTO) {
    this.lifelogDate= lifeLogDTO.getLifelogDate();
     this.emotion = lifeLogDTO.getEmotion();
     this.emotionWrite = lifeLogDTO.getEmotionWrite();
     this.bedTime = lifeLogDTO.getBedTime();
     this.wakeupTime = lifeLogDTO.getWakeupTime();
     this.activity = lifeLogDTO.getActivity();
     this.memo = lifeLogDTO.getMemo();
  }

 }
