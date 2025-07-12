package com.kurung.diagnosis.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.dto.OptionDTO;
import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.diagnosis.entity.HealthOptionEntity;
import com.kurung.diagnosis.entity.HealthQuestionEntity;
import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import com.kurung.diagnosis.enumeration.Category;
import com.kurung.diagnosis.repository.DiagnosisRepository;
import com.kurung.diagnosis.repository.DiagnosisRepositorySupport;
import com.kurung.diagnosis.repository.GoalRepository;
import com.kurung.diagnosis.repository.QuestionRepository;
import com.kurung.user.dto.UserDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

  private final GoalRepository goalRepository;
  private final DiagnosisRepository diagnosisRepository;
  private final QuestionRepository questionRepository;

  // 건강진단 질문 전체 리스트 조회
  @Override
  public List<QuestionDTO> getAllQuestions() {
    List<HealthQuestionEntity> questions = questionRepository.getAllQuestions();

    return questions.stream()
        .map(QuestionDTO::new)
        .sorted(Comparator
            .comparing((QuestionDTO question) -> Category.fromValue(question.getCategory())
                .ordinal()) // ✅ 한글 → enum
            .thenComparing(QuestionDTO::getQuestionCode)
        )
        .toList();
  }

  @Override
  public DiagnosisDTO getDiagnosisResult(String userUuid) {
    HealthDiagnosisEntity hEntity = diagnosisRepository.getDiagnosisByUserUuid(userUuid);

    if (hEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIAGNOSIS_RESULT_NOT_FOUND);
    }

    List<RecommendedGoalEntity> gEntity = goalRepository.getGoalById(hEntity.getHealthId());

    if (gEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.DIAGNOSIS_RESULT_NOT_FOUND);
    }

    List<DiagnosisDTO.GoalDTO> goalDTOList = gEntity.stream().map(
        recommendedGoalEntity -> DiagnosisDTO.GoalDTO.builder()
            .goalTitle(recommendedGoalEntity.getGoalTitle())
            .goalText(recommendedGoalEntity.getGoalText())
            .build()).collect(Collectors.toList());

    return DiagnosisDTO.builder()
        .createdAt(hEntity.getCreatedAt())
        .dianosisSummary(hEntity.getDiagnosisSummary())
        .score(hEntity.getScore())
        .goalDTOList(goalDTOList)
        .build();
  }
}
