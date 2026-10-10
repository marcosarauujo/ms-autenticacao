package com.marcos.autenticacao.application.dto.in;

import lombok.Data;

@Data
public class LoginInDTO {
    private String email;
    private String senha;
}
