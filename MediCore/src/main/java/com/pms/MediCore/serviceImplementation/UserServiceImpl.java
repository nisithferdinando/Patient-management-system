package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.response.JwtResponse;
import com.pms.MediCore.dto.request.LoginRequest;
import com.pms.MediCore.dto.request.SignupRequest;
import com.pms.MediCore.entity.User;
import com.pms.MediCore.repository.UserRepository;
import com.pms.MediCore.service.UserService;
import com.pms.MediCore.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public JwtResponse UserRegister(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())){
        throw new RuntimeException("Email Already Exists");
        }
        User user= modelMapper.map(signupRequest, User.class);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setLastModifiedDate(new Date());
        userRepository.save(user);

         String token=jwtUtil.generateToken(user.getEmail());

        JwtResponse jwtResponse=modelMapper.map(user,JwtResponse.class);
        jwtResponse.setToken(token);
        return jwtResponse;
    }

    @Override
    public JwtResponse UserLogin(LoginRequest loginRequest){
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new RuntimeException("Email Not Found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Wrong Password");
        }
        String token=jwtUtil.generateToken(user.getEmail());
        JwtResponse jwtResponse=modelMapper.map(user, JwtResponse.class);
        jwtResponse.setToken(token);
        return jwtResponse;
    }
}
