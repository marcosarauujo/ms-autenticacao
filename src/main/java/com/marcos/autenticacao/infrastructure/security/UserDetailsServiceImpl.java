package com.marcos.autenticacao.infrastructure.security;


import com.marcos.autenticacao.domain.model.Usuario;
import com.marcos.autenticacao.domain.ports.UsuarioRepositoryPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositório para acessar dados de usuário no banco de dados

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UserDetailsServiceImpl(UsuarioRepositoryPort  usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        Usuario usuario = usuarioRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuario.getSenha())
                .roles(usuario.getRole().name())
                .build(); // Constrói o objeto UserDetails
    }
}
