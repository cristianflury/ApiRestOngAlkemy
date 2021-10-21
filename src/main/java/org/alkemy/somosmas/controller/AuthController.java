package org.alkemy.somosmas.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.AuthenticationLoginRequestDto;
import org.alkemy.somosmas.dto.UserDto;
import org.alkemy.somosmas.mapper.AuthenticationLoginRequestMapper;
import org.alkemy.somosmas.mapper.UserMapper;
import org.alkemy.somosmas.security.AuthenticationService;
import org.alkemy.somosmas.security.JWTResponse;
import org.alkemy.somosmas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationLoginRequestMapper loginRequestMapper;

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> registerUser(@Valid @RequestBody UserDto userBody) throws Exception {

        userService.create(userMapper.toUser(userBody), userBody.getFileType());

        return login(new AuthenticationLoginRequestDto(userBody.getEmail(), userBody.getPassword()));

    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@Valid @RequestBody AuthenticationLoginRequestDto loginRequest) {

        return new ResponseEntity<>(
                authenticationService.createJWTResponse(loginRequestMapper
                                .toLoginRequest(loginRequest)), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(){
        return new ResponseEntity<>(userMapper.toDto(authenticationService.getLoggedInUser()), HttpStatus.FOUND);
    }
}

