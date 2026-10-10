package com.marcos.autenticacao.infrastructure.mapper;

import com.marcos.autenticacao.domain.model.Usuario;
import com.marcos.autenticacao.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity paraUsuarioEntity(Usuario usuario);

    Usuario paraUsuarioDomain(UsuarioEntity usuarioEntity);
}
