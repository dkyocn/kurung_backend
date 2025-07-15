package com.kurung.healthinfo.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_HEALTH_INFO")
public class HealthInfoEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "HEALTH_INFO_ID", nullable = false)
  private int healthinfoId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

  @Column(name = "HEIGHT")
  private float height;

  @Column(name = "WEIGHT")
  private float weight;

  @Column(name = "BODY_FAT_PERCENT")
  private float bodyfatpercent;

  @Column(name = "BMI")
  private float bmi;

  @Column(name = "MEMO")
  private String memo;


  public void updateHealthInfo(float height, float weight, float bmi, float bodyfatpercent, String memo) {
    this.height = height;
    this.weight = weight;
    this.bmi = bmi;
    this.bodyfatpercent = bodyfatpercent;
    this.memo = memo;
  }

}


