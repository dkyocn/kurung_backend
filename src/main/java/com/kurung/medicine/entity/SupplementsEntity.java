package com.kurung.medicine.entity;

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
@Entity(name = "TB_SUPPLEMENTS")
public class SupplementsEntity {
  @Id
  @Column(name = "SUPP_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int suppId;
  @Column(name = "SUPP_NAME")
  private String suppName;
  @Column(name = "SUPP_NAME_KO")
  private String suppNameKo;
  @Column(name = "CATEGORY")
  private String category;
  @Column(name = "COMPANY")
  private String company;
  @Column(name = "RECOMMENDED_SUPPLEMENTS")
  @OneToMany(mappedBy = "supplements", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<RecommendedSupplementsEntity> recommendedSupplements;
}
