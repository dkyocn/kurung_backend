package com.kurung.diagnosis.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.common.security.service.SessionService;
import com.kurung.diagnosis.dto.AnswerDTO;
import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.entity.HealthAnswerEntity;
import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.diagnosis.entity.HealthOptionEntity;
import com.kurung.diagnosis.entity.HealthQuestionEntity;
import com.kurung.diagnosis.entity.RecommendedGoalEntity;
import com.kurung.diagnosis.enumeration.Category;
import com.kurung.diagnosis.repository.AnswerRepository;
import com.kurung.diagnosis.repository.DiagnosisRepository;
import com.kurung.diagnosis.repository.GoalRepository;
import com.kurung.diagnosis.repository.OptionRepository;
import com.kurung.diagnosis.repository.QuestionRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

  private final SessionService sessionService;
  private final GoalRepository goalRepository;
  private final DiagnosisRepository diagnosisRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;
  private final OptionRepository optionRepository;
  private final UserService userService;

  // 건강진단 질문 전체 리스트 조회
  @Override
  public List<QuestionDTO> getAllQuestions() {
    List<HealthQuestionEntity> questions = questionRepository.getAllQuestions();

    if (questions.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.QUESTION_NOT_FOUND);
    }

    return questions.stream()
        .map(QuestionDTO::new)
        .sorted(Comparator
            .comparing((QuestionDTO question) -> Category.fromValue(question.getCategory())
                .ordinal()) // 한글 → enum
            .thenComparing(QuestionDTO::getQuestionCode)
        )
        .toList();
  }

  // 건강진단 결과 조회
  @Override
  public DiagnosisDTO getDiagnosisResult() {
    UserDTO userDTO = sessionService.getUserFromToken();

    HealthDiagnosisEntity hEntity = diagnosisRepository.getDiagnosisByUserUuid(userDTO.getUserUuid());

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
            .category(recommendedGoalEntity.getCategory())
            .build()).collect(Collectors.toList());

    return DiagnosisDTO.builder()
        .createdAt(hEntity.getCreatedAt())
        .dianosisSummary(hEntity.getDiagnosisSummary())
        .score(hEntity.getScore())
        .goalDTOList(goalDTOList)
        .user(hEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(hEntity.getUser())
            .build() : null)
        .build();
  }

  // 건강진단 설문 응답 저장
  @Override
  @Transactional
  public void createAnswers(List<AnswerDTO> answerList) {
    if (answerList == null || answerList.isEmpty()) {
      throw new IllegalArgumentException("일부 카테고리 응답이 누락되었습니다.");
    }

    // 세션에서 사용자 UUID 추출
    UserDTO userDTO = sessionService.getUserFromToken();

      // 기존 응답 유무 확인
      List<HealthAnswerEntity> answerEntityList = answerRepository.getByUserUuid(userDTO.getUserUuid());

      try {
        if (!answerEntityList.isEmpty()) {
          // 재검사 → 기존 응답 삭제
          answerRepository.deleteAll(answerEntityList);
        }
      } catch (Exception ex) {
        throw new CustomRunTimeException(CustomHttpStatus.ANSWER_DELETE_ERROR);
      }

      // ✅ 응답 저장
      for (AnswerDTO dto : answerList) {
        // 필수 검증
//      if (dto.getOption() == null || dto.getOption().getOptionId() <= 0) {
//        throw new IllegalArgumentException("선택지 정보가 누락되었습니다.");
//      }

        // ✅ OptionId 기준으로 옵션 리스트 조회
//      List<HealthOptionEntity> optionList = optionRepository.getByOptionId(dto.getOption().getOptionId());
//      if (optionList == null || optionList.isEmpty()) {
//        throw new IllegalArgumentException("해당 optionId에 해당하는 옵션이 없습니다. id: " + dto.getOption().getOptionId());
//      }

        //QuestionDTO questionDto = dto.();
        HealthQuestionEntity questionEntity = questionRepository.getQuestionById(
            dto.getQuestionId());
        System.out.println("👉 입력된 questionId: " + dto.getQuestionId());

        Category category = questionEntity.getCategory();
        boolean isMultiple = questionEntity.getIsMultiple() == 1;
        boolean isTextOption = dto.getOption().getTextOption() == 1;
        boolean isNullable = category == Category.ALCOHOL || category == Category.SMOKING;

        if (!isNullable && dto.getOption() == null) {
          throw new IllegalArgumentException(
              "옵션 응답은 null일 수 없습니다. questionCode: " + questionEntity.getQuestionCode());
        }
        if (isTextOption && (dto.getTextAnswer() == null || dto.getTextAnswer().isBlank())) {
          throw new IllegalArgumentException(
              "서술형 응답이 누락되었습니다. questionCode: " + questionEntity.getQuestionCode());
        }

        // ✅ 여기서 OptionId로 조회
        HealthOptionEntity optionEntity = optionRepository.getByOneOptionId(
            dto.getOption().getOptionId());
        if (dto.getOption() != null && dto.getOption().getOptionId() > 0) {
          optionEntity = optionRepository.findById(dto.getOption().getOptionId())
              .orElseThrow(() -> new IllegalArgumentException(
                  "해당 optionId에 해당하는 옵션이 없습니다. id: " + dto.getOption().getOptionId()));
        }

        HealthAnswerEntity answerEntity = HealthAnswerEntity.builder()
            .user(UserEntity.createUserBuilder().userDTO(userDTO).build())
            .option(dto.getOption() != null
                ? HealthOptionEntity.builder().optionId(dto.getOption().getOptionId()).build()
                : null)
            .textAnswer(isTextOption ? dto.getTextAnswer() : null)
            .build();

        answerRepository.save(answerEntity);
      }

  }
}



