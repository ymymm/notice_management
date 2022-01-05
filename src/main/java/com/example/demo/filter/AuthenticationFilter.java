package com.example.demo.filter;

import com.example.demo.auth.JwtTokenProvider;
import com.example.demo.common.ApiCommonCode;
import com.example.demo.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = request.getServletPath();
        if(path.equals("/user/signIn") || path.equals("/user/signUp") || path.equals("")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String token = jwtTokenProvider.resolveToken(request);
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            jwtTokenProvider.getAuthentication(token));
                    filterChain.doFilter(request, response);
                } else {
                    throw new TokenException(ApiCommonCode.INVALID_TOKEN);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new TokenException(ApiCommonCode.INVALID_TOKEN);
            }
        }

    }
}
