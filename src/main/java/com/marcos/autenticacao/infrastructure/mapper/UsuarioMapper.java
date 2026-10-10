package com.marcos.autenticacao.infrastructure.mapper;

import com.marcos.autenticacao.domain.model.Usuario;
import com.marcos.autenticacao.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity paraUsuarioEntity(Usuario usuario);

    Usuario paraUsuarioDomain(UsuarioEntity usuarioEntity);

    List<Usuario> paraListaDomain(List<UsuarioEntity> entities);


}
