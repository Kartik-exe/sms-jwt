package com.sms.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final long accessTokenValidityInMinute = 900000L; //15 mins = 60000l * 15 = 900000l;
    private final long refreshTokenValidityInHours = 28800000L; // 8 hours = 3600000l * 8;
    private final long emailTokenValidity = 300000L; // 5 mins = 60000l * 5;
    private final long emailSignInAndSignUpTokenValidity = 86400000L; // 24 hours = 6000l * 60 * 60 * 24;

    @Value("${jwt.signing.secrect}")
    private String secretKey;

    private String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Date extractIssueDate(String token) {
        return extractClaims(token, Claims::getIssuedAt);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody();
    }

    private String generateAccessToken(String loggedInUserName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loggedInUserName, accessTokenValidityInMinute);
    }

    private String generateRefreshToken(String loggedInUserName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loggedInUserName, refreshTokenValidityInHours);
    }

    private String createToken(Map<String, Object> claims, String subject, long expTime) {
        return Jwts.builder().setClaims(claims).subject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Boolean validateToken(String token, String loggedInUserName){
        final String username = extractUsername(token);
        return (username.equals(loggedInUserName) && !isTokenExpired(token));
    }

    /**
     *
     * @param userName
     * @return
     * @author KartikNigam
     */

    public String generateEmailToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName, emailTokenValidity);
    }

    public String emailSignInSignUpEmailToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName, emailSignInAndSignUpTokenValidity);
    }


    public long getRefreshTokenValidityInHours() {
        return refreshTokenValidityInHours;
    }

    public long getEmailTokenValidity() {
        return emailTokenValidity;
    }

    public long emailSignInAndSignUpTokenValidity() {
        return emailSignInAndSignUpTokenValidity;
    }

    public long getAccessTokenValidityInMinute() {
        return accessTokenValidityInMinute;
    }

}
