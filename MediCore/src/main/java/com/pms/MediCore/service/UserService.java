package com.pms.MediCore.service;


import com.pms.MediCore.dto.response.JwtResponse;
import com.pms.MediCore.dto.request.LoginRequest;
import com.pms.MediCore.dto.request.SignupRequest;

public interface UserService {

    JwtResponse UserRegister(SignupRequest signupRequest);
    JwtResponse UserLogin (LoginRequest loginRequest);
}
