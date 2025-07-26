package com.kurung.favorites.controller;

import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.enumeration.FavoritesType;
import com.kurung.favorites.service.FavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
      @ApiResponse(responseCode = "556", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<FavoritesDTO> createFavorite(@RequestBody FavoritesDTO favoritesDTO) {
    return new ResponseEntity<>(favoritesService.createFavorite(favoritesDTO),HttpStatus.OK);
  }

  @GetMapping("/list")
  @Operation(summary = "사용자 즐겨찾기 조회", description = "사용자의 즐겨찾기 전체 목록을 조회하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json"))
  @Parameter(name = "favoritesType", description = "즐겨찾기 타입", example = "RECIPE")
  public ResponseEntity<List<FavoritesDTO>> getFavoritesList( @RequestParam FavoritesType favoritesType) {
    return new ResponseEntity<>(favoritesService.getFavoritesList(favoritesType), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "즐겨찾기 삭제", description = "즐겨찾기 항목을 삭제할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "491", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "557", description = "삭제 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "삭제할 즐겨찾기 ID", example = "1")
  public ResponseEntity<HttpStatus> deleteFavorite(@PathVariable int id) {
    favoritesService.deleteFavorite(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }






}
