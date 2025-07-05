package com.kurung.diet.dto;

import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {

    protected int dietId;
    protected MEAL meal;
    protected Date dietDate;
    protected UserDTO user;


    @Builder(builderMethodName = "toDietBuilder", builderClassName = "toDietBuilder")
    public DietDTO(DietEntity dietEntity) {
        this.dietId = dietEntity.getDietId();
        this.meal = dietEntity.getMeal();
        this.dietDate = dietEntity.getDietDate();
        this.user = UserDTO.toUserBuilder()
                .userEntity(dietEntity.getUser())
                .build();
    }
}
