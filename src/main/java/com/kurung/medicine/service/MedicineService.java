package com.kurung.medicine.service;

import com.kurung.medicine.dto.InteractionResultDTO;
import com.kurung.medicine.dto.SubstanceDTO;
import com.kurung.medicine.dto.MedicineInteractionDTO;
import java.util.List;

public interface MedicineService {

  List<MedicineInteractionDTO> getInteractionResult();

  List<SubstanceDTO> getRecSupplements();

  List<SubstanceDTO> getMedicineList(String keyword);

//  void createMedicineList(List<SubstanceDTO> mediList);
}
