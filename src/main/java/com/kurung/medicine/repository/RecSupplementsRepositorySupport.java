package com.kurung.medicine.repository;

import com.kurung.medicine.entity.RecommendedSupplementsEntity;
import java.util.List;

public interface RecSupplementsRepositorySupport {
  List<RecommendedSupplementsEntity> getRecSuppById(int mediInterId);
}
