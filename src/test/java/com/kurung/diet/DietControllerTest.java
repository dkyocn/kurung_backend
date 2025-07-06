package com.kurung.diet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurung.diet.controller.DietController;
import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.diet.service.DietService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.enumeration.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DietController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("test")
public class DietControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockBean
  DietService dietService;

  DietDTO dietDummy() {
    return DietDTO.builder()
        .dietId(1)
        .dietDate(new Date(20250614))
        .meal(MEAL.BREAKFAST)
        .foodList(List.of(FoodDTO.builder()
            .foodId(1)
            .foodName("김치")
            .foodPhoto("../images/food/kimchi")
            .build()))
        .user(UserDTO.builder()
            .userUuid("2025061401")
            .userId("thdalstj0450@gmail.com")
            .userFaceLoginYN(false)
            .userPwd("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
            .userNick("송민서")
            .userGender(Gender.MALE)
            .userAge(new Date(19960401))
            .isActive(true)
            .adminYN(true)
            .build())
        .build();
  }


  @Test
  void 식단_단일_조회() throws Exception {
    given(dietService.getDietById(anyInt())).willReturn(dietDummy());
    mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/kurung/diet/{id}", 1))
        .andExpect(status().isOk())
        .andDo(document("getDietById",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("조회 할 식단의 아이디")
            ),
            relaxedResponseFields(
                fieldWithPath("dietId").description("식단 아이디").type(JsonFieldType.NUMBER),
                fieldWithPath("dietDate").description("식단 일자").type(JsonFieldType.STRING),
                fieldWithPath("meal").description("식단 시간").type(JsonFieldType.STRING),
                fieldWithPath("foodList").description("음식 리스트").type(JsonFieldType.ARRAY),
                fieldWithPath("foodList[].foodId").description("음식 아이디").type(JsonFieldType.NUMBER),
                fieldWithPath("foodList[].foodName").description("음식 이름")
                    .type(JsonFieldType.STRING),
                fieldWithPath("foodList[].foodPhoto").description("음식 사진")
                    .type(JsonFieldType.STRING),
                fieldWithPath("user").description("사용자 정보"),
                fieldWithPath("user.userUuid").description("사용자 UUID").type(JsonFieldType.STRING),
                fieldWithPath("user.userId").description("사용자 아이디").type(JsonFieldType.STRING),
                fieldWithPath("user.userFaceLoginYN").description("페이스 로그인 등록 여부")
                    .type(JsonFieldType.BOOLEAN),
                fieldWithPath("user.userPwd").description("사용자 비밀번호").type(JsonFieldType.STRING),
                fieldWithPath("user.userNick").description("사용자 닉네임").type(JsonFieldType.STRING),
                fieldWithPath("user.userGender").description("사용자 성별").type(JsonFieldType.STRING),
                fieldWithPath("user.userAge").description("사용자 생일").type(JsonFieldType.STRING),
                fieldWithPath("user.active").description("사용자 활성화 여부").type(JsonFieldType.BOOLEAN),
                fieldWithPath("user.adminYN").description("관리자 여부").type(JsonFieldType.BOOLEAN)
            )));
  }
}
