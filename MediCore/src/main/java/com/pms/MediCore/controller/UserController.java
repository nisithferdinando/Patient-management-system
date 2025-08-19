package com.pms.MediCore.controller;

import com.pms.MediCore.dto.response.JwtResponse;
import com.pms.MediCore.dto.request.LoginRequest;
import com.pms.MediCore.dto.request.SignupRequest;
import com.pms.MediCore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity <JwtResponse> UserRegister(@RequestBody SignupRequest signupRequest){
        JwtResponse jwtResponse=userService.UserRegister(signupRequest);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> UserLogin(@RequestBody LoginRequest loginRequest){
        JwtResponse jwtResponse=userService.UserLogin(loginRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
