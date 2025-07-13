package com.kurung.missions.service;

import static com.kurung.missions.entity.QMissionsEntity.missionsEntity;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsRepository;
import com.kurung.missions.repository.MissionsRepositorySupport;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;
  private final UserService userService;

  @Override
  public List<MissionsDTO> getMissionsList() {
    List<MissionsEntity> missionsById = missionsRepository.getMissionsById();

    if (missionsById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.MISSIONS_NOT_FOUND);
    }

    return missionsById.stream()
        .map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build())
        .collect(Collectors.toList());
  }

  @Override
  public List<MissionsDTO> getTodayMissions(String userUuid) {

    UserDTO userByUuid = userService.getUserByUuid(userUuid);

    LocalDate today = LocalDate.now();
    LocalDateTime startOfDay = today.atStartOfDay();         // 00:00:00
    LocalDate localDate = startOfDay.toLocalDate();

    List<MissionsEntity> missionList = missionsRepository.getMissionList(userUuid, localDate);

//    return Optional.ofNullable(missionList)
//        .orElse(Collections.emptyList())
//        .stream()
//        .map(MissionsDTO::new)
//        .collect(Collectors.toList());

    return missionList.stream()
        .map(MissionsDTO::new)
        .collect(Collectors.toList());
  }
}
