package org.alkemy.somosmas.security;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.exception.NotAuthorizedUserException;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.service.JWTService;
import org.alkemy.somosmas.service.UserService;
import org.alkemy.somosmas.util.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    public JWTResponse createJWTResponse(AuthenticationLoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest);
        String jwt = jwtService.generateToken(authentication);

        return new JWTResponse(jwt);
    }

    private Authentication authenticate(AuthenticationLoginRequest loginRequest) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("The email or password are not correct. " + e.getMessage());
        }

        return authentication;
    }

    public User getLoggedInUser() throws NotAuthorizedUserException {

        UserDetailsImpl userDetails = SecurityUtils.getLoggedInUser();
        return userService.findByEmail(userDetails.getUsername()).orElseThrow();

        }
}
