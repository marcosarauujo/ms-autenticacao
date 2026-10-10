package com.marcos.autenticacao.application.dto.in;

import com.marcos.autenticacao.domain.model.RoleEnum;
import lombok.Data;

@Data
public class UsuarioInDTO {
    private String nome;
    private String email;
    private String senha;
    private RoleEnum role;

}
