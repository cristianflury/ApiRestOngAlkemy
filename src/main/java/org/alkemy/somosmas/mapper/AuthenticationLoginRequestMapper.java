package org.alkemy.somosmas.mapper;

import org.alkemy.somosmas.dto.AuthenticationLoginRequestDto;
import org.alkemy.somosmas.security.AuthenticationLoginRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationLoginRequestMapper {

    public AuthenticationLoginRequest toLoginRequest(AuthenticationLoginRequestDto authenticationLoginRequestDto){
        AuthenticationLoginRequest loginRequest = new AuthenticationLoginRequest();
        loginRequest.setEmail(authenticationLoginRequestDto.getEmail());
        loginRequest.setPassword(authenticationLoginRequestDto.getPassword());
        return loginRequest;
    }

}
