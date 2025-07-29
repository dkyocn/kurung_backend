package com.kurung.medicine.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InteractionResultDTO {

  @Schema(description = "상호작용 아이디", example = "1")
  protected int mediInterId;
  @Schema(description = "영양제 리스트")
  protected List<SubstanceDTO> substances;
  @Schema(description = "상호작용 결과 리스트")
  protected List<MedicineInteractionDTO> medicineInteractions;
  @Schema(description = "사용자 정보")
  protected UserDTO user;

}
