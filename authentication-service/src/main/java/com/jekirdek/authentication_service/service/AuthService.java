package com.jekirdek.authentication_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jekirdek.authentication_service.model.AuthRequest;
import com.jekirdek.authentication_service.model.AuthResponse;
import com.jekirdek.authentication_service.util.JwtUtil;
import com.jekirdek.common.dto.UserDTO;
import com.jekirdek.common.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        ResponseEntity<Response> response
                = restTemplate.postForEntity("http://localhost:8073/user/create", request, Response.class);

        ObjectMapper mapper = new ObjectMapper();
        UserDTO registeredUser = mapper.convertValue(response.getBody().getResult(), UserDTO.class);

        String accessToken = jwtUtil.generate(String.valueOf(Objects.requireNonNull(registeredUser.getId())), registeredUser.getUserRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(String.valueOf(Objects.requireNonNull(registeredUser.getId())), registeredUser.getUserRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
