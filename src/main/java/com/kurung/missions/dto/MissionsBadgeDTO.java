package com.kurung.missions.dto;

import com.kurung.missions.entity.MissionsBadgeEntity;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.user.dto.UserDTO;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MissionsBadgeDTO {

  protected int badgeId;
  protected UserDTO userDTO;
  protected LocalDate missionDate;
  protected boolean missionComplete;

  @Builder(builderMethodName = "toMissionsBadgeCuilder", builderClassName = "toMissionsBadgeCuilder")
  public MissionsBadgeDTO(MissionsBadgeEntity missionsBadgeEntity) {
    this.badgeId = missionsBadgeEntity.getBadgeId();
    this.userDTO = missionsBadgeEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(missionsBadgeEntity.getUser()).build() : null;
    this.missionDate = missionsBadgeEntity.getMissionDate();
    this.missionComplete = missionsBadgeEntity.isMissionComplete();
  }
}











































