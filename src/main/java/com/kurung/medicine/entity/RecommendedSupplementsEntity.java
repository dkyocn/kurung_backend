package com.kurung.medicine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_RECOMMENDED_SUPPLEMENTS")
public class RecommendedSupplementsEntity {
  @Id
  @Column(name = "REC_SUPP_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int recSuppId;

  @JoinColumn(name = "SUPP_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private SupplementsEntity supplements;

  @JoinColumn(name = "MEDI_INTER_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private MedicineInteractionEntity medicineInteraction;
}
