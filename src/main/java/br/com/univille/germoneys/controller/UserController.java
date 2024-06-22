package br.com.univille.germoneys.controller;

import br.com.univille.germoneys.service.user.UserServiceImpl;
import br.com.univille.germoneys.service.user.auth.dto.UserLoginDto;
import br.com.univille.germoneys.service.user.auth.dto.UserTokenDto;
import br.com.univille.germoneys.service.user.dto.UserCreationDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UserCreationDto userCreationDto) {
        this.userService.create(userCreationDto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody UserLoginDto userLoginDto) {
        UserTokenDto usuarioTokenDto = this.userService.authenticate(userLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }
}