package com.marcos.autenticacao.application.dto.out;

import com.marcos.autenticacao.domain.model.RoleEnum;
import lombok.Data;

@Data
public class UsuarioOutDTO {
    private Long id;
    private String nome;
    private String email;
    private RoleEnum role;
}
