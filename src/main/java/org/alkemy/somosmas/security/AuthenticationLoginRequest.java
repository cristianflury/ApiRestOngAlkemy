package org.alkemy.somosmas.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationLoginRequest {

    private String email;
    private String password;

}
