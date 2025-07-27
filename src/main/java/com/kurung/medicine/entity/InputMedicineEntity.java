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
@Entity(name = "TB_INPUT_MEDICINE")
public class InputMedicineEntity {
  @Id
  @Column(name = "INPUT_MEDI_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int inputMediId;
  @Column(name = "RISK")
  private String risk;
  @JoinColumn(name = "MEDI1_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private MedicineEntity medicine1;
  @JoinColumn(name = "MEDI2_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private MedicineEntity medicine2;
  @JoinColumn(name = "MEDI_INTER_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private MedicineInteractionEntity medicineInteraction;
}
