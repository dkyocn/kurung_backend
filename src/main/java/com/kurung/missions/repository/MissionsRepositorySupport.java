package com.kurung.missions.repository;

import com.kurung.missions.entity.MissionsEntity;
import java.util.List;

public interface MissionsRepositorySupport {

  List<MissionsEntity> getMissionsById(int id);

}
