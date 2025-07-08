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

  @Schema(description = "약물 아이디", example = "1")
  protected int mediInterId;
  @Schema(description = "pdf 경로", example = "/reports/report_1.pdf")
  protected String reportPdfPath;
  @Schema(description = "영양제 리스트")
  protected List<SubstanceDTO> substances;
  @Schema(description = "사용자 정보")
  protected UserDTO user;

}
