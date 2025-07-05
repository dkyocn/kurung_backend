package com.kurung.diet.service;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.repository.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;


    @Override
    public DietDTO getDietById(int id) {
        return DietDTO.toDietBuilder()
                .dietEntity(dietRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 식단을 찾을 수 없습니다.")))
                .build();
    }
}
