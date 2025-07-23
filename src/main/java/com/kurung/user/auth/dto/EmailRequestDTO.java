package com.kurung.user.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "이메일 인증 요청 DTO")
public class EmailRequestDTO {

  @Schema(description = "사용자 이메일", example = "helpdesk@gmail.com")
  private String email;
  @Schema(description = "인증 코드 (검증 요청 시 필수)", example = "123456")
  private String code;
}
