package br.com.univille.germoneys.service.user;

import br.com.univille.germoneys.entity.User;
import br.com.univille.germoneys.service.user.auth.dto.UserLoginDto;
import br.com.univille.germoneys.service.user.auth.dto.UserTokenDto;
import br.com.univille.germoneys.service.user.dto.UserCreationDto;

public interface UserService {

    void create(UserCreationDto userCreationDto);
    UserTokenDto authenticate(UserLoginDto userLoginDto);
    User findByEmail(String email);
}
