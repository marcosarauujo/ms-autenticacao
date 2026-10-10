package com.marcos.autenticacao.application.mapper;

import com.marcos.autenticacao.application.dto.in.UsuarioInDTO;
import com.marcos.autenticacao.application.dto.out.UsuarioOutDTO;
import com.marcos.autenticacao.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {

    Usuario paraUsuarioDomain(UsuarioInDTO dto);

    //converter o Domain para DTO
    UsuarioOutDTO paraUsuarioOutDTO(Usuario usuario);
}
