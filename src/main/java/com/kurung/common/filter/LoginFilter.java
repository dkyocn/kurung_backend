package com.kurung.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurung.user.repository.UserRepositorySupport;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.common.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JWTUtil jwtUtil; //JWT토큰 생성
    private final UserRepositorySupport userRepositorySupport; //DB 사용자 정보 추출

    //생성자(constructor) + 외부 도구들
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepositorySupport userRepositorySupport) {
        this.setAuthenticationManager(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userRepositorySupport = userRepositorySupport;
        this.setFilterProcessesUrl("/login");
    }

    //Id+Pwd를 통해 로그인 심사 요청
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String userId = null;
        String userPwd = null;

        //특수 로그인 방식(예: facelogin + 서버에 미리 저장한)
        if (request.getAttribute("userId") != null && request.getAttribute("userPwd") != null) {
            userId = (String) request.getAttribute("userId");
            userPwd = (String) request.getAttribute("userPwd");
            log.info("UUID-based Login Request Detected : userId={}, userPwd={}", userId, userPwd);
            return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userId, userPwd));
        }
        
        //일반 로그인 인증 (1차 검증)
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> requestBody = mapper.readValue(request.getInputStream(), Map.class);
            log.info("requestBody={}", requestBody);
            userId = requestBody.get("userId");
            userPwd = requestBody.get("userPwd");
        } catch (IOException e){
            throw new RuntimeException("요청 데이터를 읽을 수 없습니다.", e);
        }
        if(userId == null || userPwd == null){
            throw new RuntimeException("아이디 또는 비밀번호가 전달되지 않았습니다.");
        }
        //실제 비밀번호
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPwd);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    //로그인 인증 (2차 검증)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException{
        log.info("로그인 성공 로직 실행");
        String userUuid = authentication.getName(); // 로그인 성공한 사용자 UUID
        UserEntity user = userRepositorySupport.findByUserUuid(userUuid); // UUID로 사용자 정보 재조회

        if(user == null){
            throw new RuntimeException("로그인 사용자를 찾을 수 없습니다." + userUuid);
        }
        if(user.getLoginOk().equals("N")){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"로그인 정지 회원입니다. 관리자에게 문의하세요\"}");
            return;
        }

        //토큰 발급
        String accessToken = jwtUtil.generateToken(user, "access");

        Map<String, Object> responseBody = Map.of(
                "accessToken", accessToken,
                "userUuid", user.getUserUuid(), //UUID로 변경
                "userName", user.getUserNick(),
                "role", user.isAdminYN() ? "ADMIN" : "USER"
        );
        response.setContentType("application/json; charset=utf-8"); //json 포맷으로 반환
        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }

    //로그인 실패시
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");

        //에러 메시지 지정
        String errorMessage = "로그인 실패 : 알 수 없는 오류가 발생했습니다.";

        if (failed.getMessage().contains("Bad credentials")) {
            errorMessage = "아이디와 비밀번호를 다시 확인해 주세요.";
        } else if (failed.getMessage().contains("사용자를 찾을 수 없습니다.")) {
            errorMessage = "해당 사용자를 찾을 수 없습니다.";
        }
        response.getWriter().write(String.format("{\"error\":\"%s\"}", errorMessage));
    }
}
