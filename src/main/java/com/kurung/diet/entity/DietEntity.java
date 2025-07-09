package com.kurung.diet.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_DIET")
public class DietEntity {

    @Id
    @Column(name = "DIET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dietId;
    @Column(name = "MEAL", nullable = false)
    @Enumerated(EnumType.STRING)
    private MEAL meal;
    @Column(name = "DIET_DATE", nullable = false)
    private Date dietDate;
    @JoinColumn(name = "USER_UUID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @Column(name = "DIET_FOOD")
    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DietFoodEntity> dietFood;
    @OneToOne(mappedBy = "diet")
    private NutritionalEntity nutritional;
}
