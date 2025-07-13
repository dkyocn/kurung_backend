package com.kurung.user.service;

import com.kurung.user.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshRepository refreshRepository;

    public void deleteRefreshToken(String userId) {
        refreshRepository.deleteByUserId(userId);
    }
}
