package br.com.univille.germoneys.service.user.auth;

import br.com.univille.germoneys.entity.User;
import br.com.univille.germoneys.repository.UserRepository;
import br.com.univille.germoneys.service.user.auth.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> usuarioOpt = userRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }

        return new UserDetailsDto(usuarioOpt.get());
    }
}