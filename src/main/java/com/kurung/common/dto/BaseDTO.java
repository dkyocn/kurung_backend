package com.kurung.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {

  @Schema(description = "생성 일자", example = "2025-07-06 12:00:00")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime createdAt;

  @Schema(description = "최종 수정 일자", example = "2025-07-06 12:00:00")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime updatedAt;
}
