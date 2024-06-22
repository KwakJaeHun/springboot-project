package com.jhkwak.preorder.controller;

import com.jhkwak.preorder.dto.user.LoginRequestDto;
import com.jhkwak.preorder.dto.user.SignupRequestDto;
import com.jhkwak.preorder.entity.Response;
import com.jhkwak.preorder.jwt.JwtUtil;
import com.jhkwak.preorder.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/signup")
    @ResponseBody
    public Response signup(SignupRequestDto requestDto){
        return userService.signup(requestDto);
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(){

        return "/user/login";

    }
    
    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public Response login(LoginRequestDto loginRequestDto, HttpServletResponse res){

        return userService.login(loginRequestDto, res);

    }

    // 로그아웃
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletResponse response){

        // jwt 삭제
        jwtUtil.jwtDelete(response);

        return ResponseEntity.ok().build();
    }
    
    
    
    // 이메일 인증
    @GetMapping("/verify")
    @ResponseBody
    public Response emailVerification(@RequestParam String token){

        return userService.emailVerification(token);

    }
}
