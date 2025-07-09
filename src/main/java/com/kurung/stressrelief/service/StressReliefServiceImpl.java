package com.kurung.stressrelief.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.stressrelief.dto.StressReliefDTO;
import com.kurung.stressrelief.entity.StressReliefEntity;
import com.kurung.stressrelief.repository.StressReliefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //스프링에게 서비스라고 알려주는 역할 태그(Annotation)
@RequiredArgsConstructor //생성자 자동 생성 도구 (롬복 애너테이션). 클래스 안에 final로 선언된 필드가 있다면, 그걸 자동으로 생성자 주입용 파라미터로 만들어줌
public class StressReliefServiceImpl implements StressReliefService {

    private final StressReliefRepository stressReliefRepository;

    @Override
    public StressReliefDTO getStressReliefById(int id) {
        StressReliefEntity entity = stressReliefRepository.getStressReliefById(id);

        if (entity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.STRESSRELIEF_NOT_FOUND);
        }

        return StressReliefDTO.toStressReliefBuilder()
                .entity(entity)
                .build();
    }
}
