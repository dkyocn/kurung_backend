package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsRepository;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;
  private final UserRepository userRepository;

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

//  @Override
//  public List<MissionsDTO> createAndGetTodayMissions(String userUuid) {
//
//    UserEntity user = userRepository.findByUserUuid(userUuid)
//        .orElseThrow(() -> new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND));
//
//    List<MissionsDTO> todayMissions = new ArrayList<>();
//    Date today = new Date(System.currentTimeMillis());
//
//    for (HealthType type : List.of(
//        HealthType.EXERCISE,
//        HealthType.STRESS,
//        HealthType.DAILY,
//        HealthType.DIET
//    )) {
//      boolean exists = missionsRepository.existsByUserUserUuidAndDisplayTypeAndStartedDate(
//          userUuid,
//          type,
//          today
//      );
//
//      MissionsEntity mission;
//
//      if (!exists) {
//        // 🔥 중복 선언 제거하고 위에서 가져온 user 사용
//        mission = MissionsEntity.builder()
//            .user(user) // 여기만 고침!
//            .displayType(type)
//            .startedDate(today)
//            .isComplete(false)
//            .toggleOption(true)
//            .build();
//
//        missionsRepository.save(mission);
//      } else {
//        mission = missionsRepository.findByUserUserUuidAndDisplayTypeAndStartedDate(
//            userUuid,
//            type,
//            today
//        );
//      }
//
//      todayMissions.add(MissionsDTO.toMissionBuilder().missionEntity(mission).build());
//    }
//
//    return todayMissions;
//  }
//}

  @Override
  public List<MissionsDTO> createAndGetTodayMissions(String userUuid) {

    // ✅ 1. 사용자 조회 (반드시 DB에 있는 사용자여야 함)
    UserEntity user = userRepository.findByUserUuid(userUuid)
        .orElseThrow(() -> new CustomIllegalArgumentException(CustomHttpStatus.USER_NOT_FOUND));

    List<MissionsDTO> todayMissions = new ArrayList<>();
    Date today = new Date(System.currentTimeMillis());

    // ✅ 2. 미션 4개 생성 또는 조회
    for (HealthType type : List.of(
        HealthType.EXERCISE,
        HealthType.STRESS,
        HealthType.DAILY,
        HealthType.DIET
    )) {
      boolean exists = missionsRepository.existsByUserUserUuidAndDisplayTypeAndStartedDate(
          userUuid,
          type,
          today
      );

      MissionsEntity mission;
      if (!exists) {
        // ✅ DB에서 조회한 user로 설정
        mission = MissionsEntity.builder()
            .user(user)
            .displayType(type)
            .startedDate(today)
            .isComplete(false)
            .toggleOption(true)
            .build();
        mission = missionsRepository.save(mission);
      } else {
        mission = missionsRepository.findByUserUserUuidAndDisplayTypeAndStartedDate(
            userUuid,
            type,
            today
        );
      }

      todayMissions.add(MissionsDTO.toMissionBuilder().missionEntity(mission).build());
    }

    return todayMissions;
  }
}