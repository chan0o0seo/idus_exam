package com.example.idusexam.util;

import com.example.idusexam.util.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String h = request.getHeader("Content-Type");
        System.out.println("content-type = "+h);

        String header = request.getHeader("Authorization");
        System.out.println("Authorization = "+header);

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");

        try {
            System.out.println("!#31242");
            Claims claims = JWTUtil.extractClaims(token);
            String username = claims.getSubject();

            if ( SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = null;
                try {
                    userDetails = userDetailsService.loadUserByUsername(username);
                } catch (UsernameNotFoundException e) {
                    System.out.println("❌ 존재하지 않는 사용자: " + username);
                    response.setStatus(HttpServletResponse.SC_OK); // 403 대신 200 상태 코드
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\": \"아이디 또는 비밀번호가 잘못되었습니다.\"}");
                    return;
                }

                if (userDetails != null) {
                    Authentication authentication = new JWTAuthenticationToken(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ JWT 검증 실패: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_OK); // 403 대신 200 상태 코드
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"아이디 또는 비밀번호가 잘못되었습니다.\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
