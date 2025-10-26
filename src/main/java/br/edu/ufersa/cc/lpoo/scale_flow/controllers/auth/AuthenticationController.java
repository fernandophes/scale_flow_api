package br.edu.ufersa.cc.lpoo.scale_flow.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.auth.LoginRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.auth.LoginResponse;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.services.auth.TokenService;
import br.edu.ufersa.cc.lpoo.scale_flow.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Auth")
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager manager;
    private UserService service;
    private PasswordEncoder encoder;
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Fazer login", security = {})
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        val usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword());

        val authentication = manager.authenticate(usernamePassword);
        val token = tokenService.generateToken((UserDto) authentication.getPrincipal());
        val response = new LoginResponse().setToken(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Cadastrar-se", security = {})
    public ResponseEntity<UserDto> register(@RequestBody UserRequest request) {
        // Criptografar a senha
        val encrypted = encoder.encode(request.getPassword());
        request.setPassword(encrypted);

        return ResponseEntity.ok(service.create(request));
    }

}
