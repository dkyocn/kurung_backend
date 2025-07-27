package com.kurung.diet.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_ALLERGY")
public class AllergyEntity {

  @Id
  @Column(name = "ALLERGY_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int allergyId;

  @Column(name = "ALLERGY_NAME")
  private String allergyName;

  @OneToMany(mappedBy = "allergy", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<FoodAllergyEntity> foodAllergy;
}
