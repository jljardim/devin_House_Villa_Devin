package dev.in.villaDevin.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dev.in.villaDevin.model.transport.JwtTokenDTO;
import dev.in.villaDevin.model.transport.LoginRequestDTO;
import dev.in.villaDevin.security.JWTTokenUtils;
import dev.in.villaDevin.security.UserDetailsImpl;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    JWTTokenUtils jwtUtil;
    AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTTokenUtils jwtUtil) {
        setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException exception) throws IOException, ServletException {
                response.setStatus(401);
                response.setContentType("application/json");
                response.getWriter().append(json());
            }

            private String json() {

                long date = new Date().getTime();
                return "{\"timestamp\": " + date + ", "
                        + "\"status\": 401, "
                        + "\"error\": \"Não autorizado\", "
                        + "\"message\": \"Email ou senha inválidos\", "
                        + "\"path\": \"/login\"}";
            }
        });
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        try {
            ServletInputStream stream = request.getInputStream();
            LoginRequestDTO credentials = new ObjectMapper().readValue(stream, LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(), credentials.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        JwtTokenDTO generatedToken = jwtUtil.generateToken(username);

        response.addHeader("Authorization", generatedToken.getFullToken());
        response.getWriter().append(new Gson().toJson(generatedToken));
    }
}
