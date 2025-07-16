package com.kurung.favorites.controller;

import com.kurung.common.enumeration.HealthType;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.enumeration.FavoritesType;
import com.kurung.favorites.service.FavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/favorites")
@Tag(name = "FAVORITES API TEST", description = "즐겨찾기 API 테스트용입니다.")
public class FavoritesController {

  private final FavoritesService favoritesService;

  @PostMapping("/create")
  @Operation(summary = "즐겨찾기 저장", description = "새 즐겨찾기를 등록할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "579", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> createFavorite(@RequestBody FavoritesDTO favoritesDTO) {
    favoritesService.createFavorite(favoritesDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/list")
  @Operation(summary = "사용자 즐겨찾기 조회", description = "사용자의 즐겨찾기 전체 목록을 조회하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json"))
  @Parameter(name = "userUuid", description = "사용자 UUID", example = "2025061406")
  public ResponseEntity<List<FavoritesDTO>> getFavoritesList(@RequestParam String userUuid , @RequestParam FavoritesType favoritesType) {
    return new ResponseEntity<>(favoritesService.getFavoritesList(userUuid,favoritesType), HttpStatus.OK);
  }





}
