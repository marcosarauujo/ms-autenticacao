package com.marcos.autenticacao.infrastructure.controller;

import com.marcos.autenticacao.application.dto.in.LoginInDTO;
import com.marcos.autenticacao.application.dto.in.UsuarioInDTO;
import com.marcos.autenticacao.application.dto.out.UsuarioOutDTO;
import com.marcos.autenticacao.application.service.AutenticacaoService;
import com.marcos.autenticacao.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AutenticacaoService autenticacaoService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping("/registrar")
    public ResponseEntity<UsuarioOutDTO> registrar(@RequestBody UsuarioInDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autenticacaoService.registar(dto));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginInDTO loginInDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginInDTO.getEmail(), loginInDTO.getSenha()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }
}
