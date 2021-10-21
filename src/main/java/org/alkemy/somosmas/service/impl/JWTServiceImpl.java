package org.alkemy.somosmas.service.impl;

import io.jsonwebtoken.*;
import org.alkemy.somosmas.security.UserDetailsImpl;
import org.alkemy.somosmas.service.JWTService;
import org.alkemy.somosmas.util.EncodeBase64Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {


    private final String secretKey;
    private final Long expTimeMillis;

    public JWTServiceImpl(@Value("${somos-mas.security.jwt.secret-key}") String secretKey,
                          @Value("${somos-mas.jwt.exp.time}") Long expTimeMillis) {
        this.secretKey = EncodeBase64Utils.encode(secretKey);
        this.expTimeMillis = expTimeMillis;
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    @Override
    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return createToken(userDetails.getUserName());
    }

    private String createToken(String email) {

        return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expTimeMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new IllegalStateException("Invalid JWT signature " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new IllegalStateException("Invalid JWT token " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new IllegalStateException("JWT token is expired " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new IllegalStateException("JWT token is unsupported " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("JWT claims string is empty " + e.getMessage());
        }

    }

    @Override
    public String parseJwt(HttpServletRequest httpServletRequest) {
        String headerAuth = httpServletRequest.getHeader("Authorization");
        return (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
                ? headerAuth.substring(7) : null;
    }
}

