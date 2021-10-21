package org.alkemy.somosmas.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Function;

public interface JWTService {

    String extractEmail(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Authentication authentication);

    Boolean validateToken(String token);

    String parseJwt(HttpServletRequest httpServletRequest);

}
