package com.kurung.medicine.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "TB_MEDICINE_INTERACTION")
public class MedicineInteractionEntity {
  @Id
  @Column(name = "MEDI_INTER_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int mediInterId;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @Column(name = "INTERACTION")
  private String interaction;
  @Column(name = "REPORT_PDF_PATH")
  private String reportPdfPath;

  @Column(name = "INPUT_MEDICINE")
  @OneToMany(mappedBy = "medicineInteraction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<InputMedicineEntity> inputMedicine;

  @Column(name = "RECOMMENDED_SUPPLEMENTS")
  @OneToMany(mappedBy = "medicineInteraction", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<RecommendedSupplementsEntity> recommendedSupplements;
}
