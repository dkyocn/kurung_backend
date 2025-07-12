package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsRepository;
import com.kurung.missions.repository.MissionsRepositorySupport;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;
  private final UserRepository userRepository;
  private final MissionsRepositorySupport missionsRepositorySupport;

  @Override
  public List<MissionsDTO> getMissionsList() {
    List<MissionsEntity> missionsById = missionsRepository.getMissionsById();

    if (missionsById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.FAVORITE_NOT_FOUND);
    }

    return missionsById.stream()
        .map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build())
        .collect(Collectors.toList());
  }

  @Override
  public List<MissionsDTO> createAndGetTodayMissions(String userUuid) {
    UserEntity user = userRepository.findByUserUuid(userUuid)
        .orElseThrow(() -> new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND));

    Date today = Date.valueOf(LocalDate.now());
    List<MissionsDTO> todayMissions = new ArrayList<>();

    for (HealthType type : List.of(HealthType.EXERCISE, HealthType.STRESS, HealthType.DAILY, HealthType.DIET)) {

      boolean exists = missionsRepositorySupport.existsMission(userUuid, type, today);
      MissionsEntity mission;

      if (!exists) {
        mission = MissionsEntity.builder()
            .user(user)
            .displayType(type)
            .startedDate(today)
            .isComplete(false)
            .toggleOption(true)
            .build();

        mission = missionsRepository.save(mission);
      } else {
        mission = missionsRepositorySupport.findMission(userUuid, type, today);
      }

      todayMissions.add(MissionsDTO.toMissionBuilder().missionEntity(mission).build());
    }

    return todayMissions;
  }

  @Override
  public ResponseEntity<MissionsDTO> updateMission(MissionsDTO dto) {
    Optional<MissionsEntity> optional = missionsRepository.findById(dto.getMissionId());

    if (optional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    MissionsEntity entity = optional.get();

    entity = MissionsEntity.builder()
        .missionId(entity.getMissionId())
        .user(entity.getUser()) // 기존 사용자 유지
        .startedDate(dto.getStartedDate())
        .isComplete(dto.isComplete())
        .displayType(dto.getDisplayType())
        .toggleOption(dto.isToggleOption())
        .build();

    MissionsEntity updated = missionsRepository.save(entity);
    return ResponseEntity.ok(MissionsDTO.toDTO(updated));
  }
}
