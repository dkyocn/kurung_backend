package com.kurung.diet.controller;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.service.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/diet")
public class DietController {

    private final DietService dietService;

    @GetMapping("/{id}")
    public ResponseEntity<DietDTO> getDietById(@PathVariable int id) {
        return new ResponseEntity<>(dietService.getDietById(id), HttpStatus.OK);
    }
}
