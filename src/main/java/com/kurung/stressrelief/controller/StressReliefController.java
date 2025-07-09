package com.kurung.stressrelief.controller;

import com.kurung.stressrelief.dto.StressReliefDTO;
import com.kurung.stressrelief.service.StressReliefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j  // 로그를 출력할 수 있게 해주는 Lombok 어노테이션
@RestController  //이 클래스가 REST API 요청을 처리하는 컨트롤러임을 스프링에게 알려주는 어노테이션. 클라이언트(예: 프론트엔드)에서 보내는 HTTP 요청(GET/POST 등)을 이 클래스가 처리하게 만든다는 뜻이야.
@RequiredArgsConstructor  //이 클래스의 final로 선언된 필드들을 자동으로 생성자 주입해주는 Lombok 어노테이션
@Tag(name = "STRESSRELIEF API TEST", description = "스트레스해소 API 테스트코드입니다.") //Swagger UI에서 이 API 그룹은 **“STRESSRELIEF API TEST”**라는 이름으로 보여지고, 하단 설명에는 “스트레스해소 API 테스트코드입니다.” 라고 표시됨
@RequestMapping("/api/v1/kurung/stressrelief") //이 컨트롤러에서 처리할 모든 API의 기본 URL 경로(prefix) 를 지정하는 어노테이션
public class StressReliefController {

    private final StressReliefService stressreliefService; //스트레스 해소 기능을 대신 해주는 전문가(Service)를 클래스 안에 고정으로 둔 것. 컨트롤러가 직접 처리하면 너무 많은 책임이 생기고, 코드가 지저분해짐

    @GetMapping("/{id}") //@GetMapping → HTTP 요청 중 "조회(Read)"에 해당하는 GET 요청을 처리하라는 뜻. "/{id}" → 주소에 변수(=id)가 들어올 수 있다는 뜻
    @Operation(summary = "스트레스해소 단일 조회", description = "하나의 스트레스해소 조회시 사용하는 API") //이 API의 역할을 Swagger가 알아서 정리해주고, 팀원 누구나 웹 브라우저에서 API 설명서처럼 볼 수 있음
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")), //Swagger 문서 페이지에 “성공 시는 200, 실패 시는 418”
            @ApiResponse(responseCode = "418", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")) //content = @Content(mediaType = "application/json")
    })
    @Parameter(name = "id", description = "스트레스 조회 아이디", example = "1") //id는 "어떤 스트레스 해소 항목을 조회할지 고르는 숫자"라는 뜻
    public ResponseEntity<StressReliefDTO> getStressReliefById(@PathVariable int id) {
        return new ResponseEntity<>(stressreliefService.getStressReliefById(id), HttpStatus.OK); //URL에서 넘겨받은 id 값으로, 해당하는 스트레스 해소 정보를 찾아서 JSON으로 돌려주는 HTTP 응답(Response)을 만들어주는 메서드야.
    }
}

//stressreliefService.getStressReliefById(id) 	서비스에서 id로 데이터 조회함 (엔티티 → DTO 변환됨)
//new ResponseEntity<>(...) HTTP 응답(Response) 객체를 직접 만들어 줌
//HttpStatus.OK 상태코드 200 OK (성공했단 뜻)








