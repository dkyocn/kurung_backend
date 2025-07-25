package com.kurung.medicine.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.medicine.dto.SubstanceDTO;
import com.kurung.medicine.entity.SupplementsEntity;
import com.kurung.medicine.enumeration.MEDICINE;
import com.kurung.medicine.repository.MedicineRepository;
import com.kurung.medicine.repository.SupplementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
  private final MedicineRepository medicineRepository;
  private final SupplementsRepository supplementsRepository;

  @Override
  public SubstanceDTO getSuppById(int suppId) {

    SupplementsEntity supplementsEntity = supplementsRepository.getSuppById(suppId);

    if (supplementsEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND);
    }

    return SubstanceDTO.builder()
        .substanceId(supplementsEntity.getSuppId())
        .name(supplementsEntity.getSuppName())
        .nameKo(supplementsEntity.getSuppNameKo())
        .imagePath(supplementsEntity.getImagePath())
        .category(supplementsEntity.getCategory())
        .company(supplementsEntity.getCompany())
        .medicine(MEDICINE.SUPPLEMENTS)
        .build();
  }
}
