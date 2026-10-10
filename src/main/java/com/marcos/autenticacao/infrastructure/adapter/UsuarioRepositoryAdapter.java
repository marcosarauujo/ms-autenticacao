package com.marcos.autenticacao.infrastructure.adapter;

import com.marcos.autenticacao.domain.model.Usuario;
import com.marcos.autenticacao.domain.ports.UsuarioRepositoryPort;
import com.marcos.autenticacao.infrastructure.entity.UsuarioEntity;
import com.marcos.autenticacao.infrastructure.mapper.UsuarioMapper;
import com.marcos.autenticacao.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor

public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(usuarioMapper::paraUsuarioDomain);

    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioMapper.paraUsuarioEntity(usuario);
        UsuarioEntity save = usuarioRepository.save(usuarioEntity);
        return usuarioMapper.paraUsuarioDomain(save);

    }

    @Override
    public List<Usuario> findAll() {
        return usuarioMapper.paraListaDomain(usuarioRepository.findAll());
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::paraUsuarioDomain);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
