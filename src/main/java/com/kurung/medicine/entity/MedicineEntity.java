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
@Entity(name = "TB_MEDICINE")
public class MedicineEntity {

  @Id
  @Column(name = "MEDI_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int mediId;
  @Column(name = "MEDI_NAME")
  private String mediName;
  @Column(name = "MEDI_NAME_KO")
  private String mediNameKo;
  @Column(name = "CATEGORY")
  private String category;
  @Column(name = "COMPANY")
  private String company;
  @Column(name="INPUT_MEDICINE")
  @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<InputMedicineEntity> inputMedicine;
}
