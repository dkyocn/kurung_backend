package com.kurung.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 API 응답 DTO")
public class ApiResponseDTO<T> {
    
    @Schema(description = "성공 여부", example = "true")
    private boolean success;
    
    @Schema(description = "응답 메시지", example = "처리가 성공적으로 완료되었습니다.")
    private String message;
    
    @Schema(description = "응답 데이터")
    private T data;
    
    @Schema(description = "응답 시간", example = "2025-01-23T10:30:00")
    private LocalDateTime timestamp;
} 