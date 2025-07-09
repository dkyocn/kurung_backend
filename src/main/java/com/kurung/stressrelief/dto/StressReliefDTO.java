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

    @Schema(description = "스트레스 해소 ID", example = "1") //@Schema는 Swagger 문서에서 이 필드를 설명할 때 쓰는 문서 주석 어노테이션
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


//@Builder => new 없이도 toStressReliefBuilder().entity(엔티티).build() 같은 방식으로 DTO 만들 수 있음
//public StressReliefDTO(StressReliefEntity entity) => 엔티티를 받아서 DTO에 각각 필드를 채워주는 변환 생성자야
//this.stressReliefId = entity.getStressReliefId(); => 엔티티의 ID 값을 DTO에 복사
//this.stressReliefTitle = entity.getStressReliefTitle(); => 제목도 복사











