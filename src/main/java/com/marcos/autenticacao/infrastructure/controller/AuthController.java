package com.marcos.autenticacao.infrastructure.controller;

import com.marcos.autenticacao.application.dto.in.LoginInDTO;
import com.marcos.autenticacao.application.dto.in.UsuarioInDTO;
import com.marcos.autenticacao.application.dto.out.UsuarioOutDTO;
import com.marcos.autenticacao.application.service.AutenticacaoService;
import com.marcos.autenticacao.infrastructure.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

@Tag(name = "Autenticação", description = "Endpoints para login e gestão de funcionários")

public class AuthController {

    private final AutenticacaoService autenticacaoService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping("/registrar")
    @PreAuthorize("hasRole('GERENTE')")

    @Operation(summary = "Cadastrar funcionário",
            description = "Cria um novo garçom, caixa ou gerente. Apenas Gerentes podem acessar.")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado (não é gerente)")

    public ResponseEntity<UsuarioOutDTO> registrar(@RequestBody UsuarioInDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autenticacaoService.registar(dto));
    }

    @PostMapping("/login")

    @Operation(summary = "Fazer login", description = "Recebe e-mail e senha e devolve o Token JWT.")
    @ApiResponse(responseCode = "200", description = "Token gerado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public String login(@RequestBody LoginInDTO loginInDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginInDTO.getEmail(), loginInDTO.getSenha()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GERENTE', 'CAIXA')")

    @Operation(summary = "Listar funcionários",
            description = "Retorna todos os usuários cadastrados. Apenas Gerentes e Caixas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")

    public ResponseEntity<List<UsuarioOutDTO>> listarTodosFuncionarios() {
        return ResponseEntity.ok(autenticacaoService.listarTodosFuncionarios());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GERENTE', 'CAIXA')")

    @Operation(summary = "Atualizar funcionário",
            description = "Atualiza os dados (exceto senha) de um usuário existente.")

    public ResponseEntity<UsuarioOutDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioInDTO dto) {
        return ResponseEntity.ok(autenticacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")

    @Operation(summary = "Deletar funcionário",
            description = "Exclui um usuário do sistema. Apenas Gerentes.")
    @ApiResponse(responseCode = "204", description = "Excluído com sucesso")

    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autenticacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
