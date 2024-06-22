package br.com.univille.germoneys.service.user.dto;

import br.com.univille.germoneys.entity.User;
import br.com.univille.germoneys.service.user.auth.dto.UserTokenDto;

public class UserMapper {

    public static User of(UserCreationDto userCreationDto) {
        User user = new User();

        user.setEmail(userCreationDto.getEmail());
        user.setName(userCreationDto.getName());
        user.setPassword(userCreationDto.getPassword());

        return user;
    }

    public static UserTokenDto of(User user, String token) {
        UserTokenDto usuarioTokenDto = new UserTokenDto();

        usuarioTokenDto.setUserId(user.getId());
        usuarioTokenDto.setEmail(user.getEmail());
        usuarioTokenDto.setName(user.getName());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
