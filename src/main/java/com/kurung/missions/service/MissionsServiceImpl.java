package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;

  @Override
  public MissionsDTO getMissions(int id) {
    return null;
  }

  @Override
  public MissionsDTO getMissionsById(int id) {
    List<MissionsEntity> missionsById = missionsRepository.getMissionsById(id);

    if (missionsById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.FAVORITE_NOT_FOUND);
    }

    return (MissionsDTO) missionsById.stream().map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build()).collect(Collectors.toList());
  }

}
