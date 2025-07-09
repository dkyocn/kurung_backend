package com.kurung.diet.service;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;

public interface DietService {

    DietDTO getDietById(int id);
    DietScoreDTO getDietScoreById(int id);
}
