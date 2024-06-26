package br.com.univille.germoneys.service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationDto {

    @Size(min = 3, max = 10)
    @Schema(description = "Nome do usuário", example = "Júlia")
    private String name;

    @Email
    @Schema(description = "Email do usuário", example = "germoneys@univille.br")
    private String email;

    @Size(min = 6, max = 20)
    @Schema(description = "Senha do usuário", example = "123456")
    private String password;
}