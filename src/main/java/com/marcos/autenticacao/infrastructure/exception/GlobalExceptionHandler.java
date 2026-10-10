package com.marcos.autenticacao.infrastructure.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundExcpetion.class)
    public ResponseEntity<ErroResposta> ResourceNotFoundExceptionHandle(ResourceNotFoundExcpetion ex,
                                                                        HttpServletRequest request){
        ErroResposta erro = ErroResposta.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .erro("Não Encontrado")
                .mensagem(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErroResposta> conflictExceptionHandle(ConflictException ex, HttpServletRequest request) {
        ErroResposta erro = ErroResposta.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .erro("Acesso Negado")
                .mensagem("E-mail ou senha incorretos.")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

}
