package com.kurung.medicine.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.security.service.SessionService;
import com.kurung.medicine.dto.InteractionResultDTO;
import com.kurung.medicine.dto.MedicineInteractionDTO;
import com.kurung.medicine.dto.SubstanceDTO;
import com.kurung.medicine.entity.InputMedicineEntity;
import com.kurung.medicine.entity.MedicineEntity;
import com.kurung.medicine.entity.RecommendedSupplementsEntity;
import com.kurung.medicine.repository.InputMedicineRepository;
import com.kurung.medicine.repository.RecSupplementsRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.medicine.entity.MedicineInteractionEntity;
import com.kurung.medicine.entity.SupplementsEntity;
import com.kurung.medicine.enumeration.MEDICINE;
import com.kurung.medicine.repository.InteractionRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.kurung.medicine.repository.MedicineRepository;
import com.kurung.medicine.repository.SupplementsRepository;


@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

  private final SessionService sessionService;
  private final MedicineRepository medicineRepository;
  private final SupplementsRepository supplementsRepository;
  private final InteractionRepository interactionRepository;
  private final InputMedicineRepository inputMedicineRepository;
  private final RecSupplementsRepository recSupplementsRepository;

  // 상호작용 결과 조회
  @Override
  public List<MedicineInteractionDTO> getInteractionResult() {

    UserDTO userDTO = sessionService.getUserFromToken();

    List<MedicineInteractionEntity> interactionList = interactionRepository.getResultByUser(
        userDTO.getUserUuid());

    if (interactionList == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.INTERACTIONS_NOT_FOUND);
    }

    List<MedicineInteractionDTO> resultList = new ArrayList<>();

    for (MedicineInteractionEntity interaction : interactionList) {
      int mediInterId = interaction.getMediInterId();

      // getInputMediById는 List를 반환하므로 첫 번째 값만 사용
      List<InputMedicineEntity> inputList = inputMedicineRepository.getInputMediById(mediInterId);
      if (inputList.isEmpty())
        continue;

      InputMedicineEntity input = inputList.get(0);  // 단건만 처리한다고 가정

      // 약물 1
      SubstanceDTO medi1 = SubstanceDTO.builder()
          .nameKo(input.getMedicine1().getMediNameKo())
          .substanceId(input.getMedicine1().getMediId())
          .build();

      // 약물 2
      SubstanceDTO medi2 = SubstanceDTO.builder()
          .nameKo(input.getMedicine2().getMediNameKo())
          .substanceId(input.getMedicine2().getMediId())
          .build();

      // 결과 조립
      MedicineInteractionDTO dto = MedicineInteractionDTO.builder()
          .mediInterId(mediInterId)
          .medicine1(medi1)
          .medicine2(medi2)
          .risk(input.getRisk())
          .interResult(interaction.getInteraction())
          .build();

      resultList.add(dto);
    }

    return resultList;
  }

  // 추천 영양제 조회
  @Override
  public List<SubstanceDTO> getRecSupplements(){
    UserDTO userDTO = sessionService.getUserFromToken();

    // 해당 유저의 모든 상호작용 ID 조회
    List<MedicineInteractionEntity> interactions =
        interactionRepository.getResultByUser(userDTO.getUserUuid());

    List<SubstanceDTO> resultList = new ArrayList<>();

    if (resultList == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.REC_SUPP_NOT_FOUND);
    }

    for (MedicineInteractionEntity interaction : interactions) {
      int mediInterId = interaction.getMediInterId();

      List<RecommendedSupplementsEntity> recSupps =
          recSupplementsRepository.getRecSuppById(mediInterId);

      for (RecommendedSupplementsEntity rec : recSupps) {
        SupplementsEntity supp = rec.getSupplements();

        SubstanceDTO dto = SubstanceDTO.builder()
            .substanceId(supp.getSuppId())
            .nameKo(supp.getSuppNameKo())
            .category(supp.getCategory())
            .build();

        resultList.add(dto);
      }
    }
    return resultList;
  }

  // 약물 리스트 조회
  @Override
  public List<SubstanceDTO> getMedicineList(String keyword) {
    List<MedicineEntity> medicineList = medicineRepository.getMedicineList(keyword);

    return medicineList.stream()
        .map(medicineEntity -> SubstanceDTO.builder()
            .substanceId(medicineEntity.getMediId())
            .name(medicineEntity.getMediName())
            .nameKo(medicineEntity.getMediNameKo())
            .category(medicineEntity.getCategory())
            .company(medicineEntity.getCompany())
            .medicine(MEDICINE.MEDICINE)
            .build()).collect(
                Collectors.toList()
        );
  }


}
