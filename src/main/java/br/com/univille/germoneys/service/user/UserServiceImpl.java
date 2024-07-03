package br.com.univille.germoneys.service.user;

import br.com.univille.germoneys.entity.User;
import br.com.univille.germoneys.repository.UserRepository;
import br.com.univille.germoneys.security.jwt.JwtTokenManager;
import br.com.univille.germoneys.service.user.auth.dto.UserLoginDto;
import br.com.univille.germoneys.service.user.auth.dto.UserTokenDto;
import br.com.univille.germoneys.service.user.dto.UserCreationDto;
import br.com.univille.germoneys.service.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void create(UserCreationDto userCreationDto) {
        final User newUser = UserMapper.of(userCreationDto);

        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        this.userRepository.save(newUser);
    }

    @Override
    public UserTokenDto authenticate(UserLoginDto userLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        User authenticatedUser =
                userRepository.findByEmail(userLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtTokenManager.generateToken(authentication);

        return UserMapper.of(authenticatedUser, token);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
