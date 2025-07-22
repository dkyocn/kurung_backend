package com.kurung.common.security.service;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@AllArgsConstructor
@Service
@Slf4j
public class SessionService {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    //사용자 Uuid를 추출해서 정보를 가져오기
    public UserDTO getUserFromToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        try {
            //1단계. JWTUtil에서 Uuid 추출하기
            String userUuid = jwtUtil.getUserUuidFromToken(authorizationHeader.trim());
            //2단계. UserService로 사용자 정보 조회하기
            return userService.getUserByUuid(userUuid);
            //예외 처리는 필수로 처리하자! ㅋㅋ
        } catch (Exception e) {
            log.error("토큰 분석 혹은 사용자 조회에 실패했습니다.", e);
            return null;
        }
    }

    public UserDTO getUserFromToken() {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return getUserFromToken(request); // 기존에 있던 메서드 재사용!
    }



}


