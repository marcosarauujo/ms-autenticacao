package com.marcos.autenticacao.domain.ports;

import com.marcos.autenticacao.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);
}
