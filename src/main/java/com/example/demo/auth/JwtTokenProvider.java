package com.example.demo.auth;

import com.example.demo.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private final UserService userService;

    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(long userId){
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        return createToken(claims, ACCESS_TOKEN_VALID_TIME);
    }

    private String createToken(Claims claims, long tokenValidityPeriod) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + tokenValidityPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) throws Exception {
        UserDetails userDetails = userService.findById(Long.parseLong(this.getUserId(token)));
        return new UsernamePasswordAuthenticationToken(userDetails, "");
    }

    public String getUserId(String token) throws Exception{
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) throws Exception{
        return req.getHeader("AUTHORIZATION").substring("Bearer ".length());
    }

    public boolean validateToken(String jwtToken) throws Exception{
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        return !claims.getBody().getExpiration().before(new Date());

    }
}