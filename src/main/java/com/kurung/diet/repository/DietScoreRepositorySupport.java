package com.kurung.diet.repository;

import com.kurung.diet.entity.DietScoreEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface DietScoreRepositorySupport {


  DietScoreEntity getDietScoreById(int foodId);

  DietScoreEntity getDietScoreByDate(LocalDateTime startDate, LocalDateTime endDate, String userUuid);

  List<DietScoreEntity> getDietScoreMonthList(LocalDateTime startDate, LocalDateTime endDate, String userUuid);
}
