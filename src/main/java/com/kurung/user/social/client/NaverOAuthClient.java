package com.kurung.user.social.client;

import com.kurung.user.social.dto.NaverUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOAuthClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${social.naver.api-url:https://openapi.naver.com}")
    private String naverApiUrl;
    
    /**
     * 네이버 액세스 토큰으로 사용자 정보 조회
     * @param accessToken 네이버 액세스 토큰
     * @return 네이버 사용자 정보
     */
    public NaverUserInfo getUserInfo(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            String url = naverApiUrl + "/v1/nid/me";
            ResponseEntity<NaverUserInfo> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, NaverUserInfo.class);
            
            log.info("네이버 사용자 정보 조회 성공");
            return response.getBody();
            
        } catch (Exception e) {
            log.error("네이버 사용자 정보 조회 실패: {}", e.getMessage());
            throw new RuntimeException("네이버 사용자 정보 조회에 실패했습니다.", e);
        }
    }
    
    /**
     * 네이버 토큰 유효성 검증
     * @param accessToken 네이버 액세스 토큰
     * @return 유효성 여부
     */
    public boolean validateToken(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            String url = naverApiUrl + "/v1/nid/me";
            ResponseEntity<Object> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Object.class);
            
            log.info("네이버 토큰 유효성 검증 성공");
            return response.getStatusCode().is2xxSuccessful();
            
        } catch (Exception e) {
            log.error("네이버 토큰 유효성 검증 실패: {}", e.getMessage());
            return false;
        }
    }
}
