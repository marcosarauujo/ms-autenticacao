package com.marcos.autenticacao.infrastructure.exception;

public class ResourceNotFoundExcpetion extends RuntimeException {
    public ResourceNotFoundExcpetion(String mensagem) {
        super(mensagem);
    }
}
