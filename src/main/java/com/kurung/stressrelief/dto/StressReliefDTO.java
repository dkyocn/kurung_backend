package com.kurung.stressrelief.dto;

import com.kurung.stressrelief.entity.StressReliefEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StressReliefDTO {

    @Schema(description = "스트레스 해소 ID", example = "1")
    private int stressReliefId;

    @Schema(description = "해소 제목", example = "산책")
    private String stressReliefTitle;

    @Schema(description = "해소 효과", example = "기분 전환")
    private String stressReliefEffect;

    @Schema(description = "해소 방법", example = "밖에 나가서 30분 걷기")
    private String stressRelief;

    @Builder(builderMethodName = "toStressReliefBuilder", builderClassName = "toStressReliefBuilder")
    public StressReliefDTO(StressReliefEntity entity) {
        this.stressReliefId = entity.getStressReliefId();
        this.stressReliefTitle = entity.getStressReliefTitle();
        this.stressReliefEffect = entity.getStressReliefEffect();
        this.stressRelief = entity.getStressRelief();
    }
}











