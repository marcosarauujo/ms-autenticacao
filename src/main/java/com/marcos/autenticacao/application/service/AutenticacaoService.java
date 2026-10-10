package com.marcos.autenticacao.application.service;

import com.marcos.autenticacao.application.dto.in.UsuarioInDTO;
import com.marcos.autenticacao.application.dto.out.UsuarioOutDTO;
import com.marcos.autenticacao.application.mapper.UsuarioDtoMapper;
import com.marcos.autenticacao.domain.model.Usuario;
import com.marcos.autenticacao.domain.ports.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final UsuarioDtoMapper usuarioDtoMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioOutDTO registar(UsuarioInDTO dto){
        Usuario usuario = usuarioDtoMapper.paraUsuarioDomain(dto);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioDtoMapper.paraUsuarioOutDTO(usuarioRepositoryPort.save(usuario));
    }
}
