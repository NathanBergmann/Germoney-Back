package br.com.univille.germoneys.service.user.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserLoginDto {

    @Schema(description = "E-mail do usuário", example = "admin@germoneys.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "GermoneysAdmin22062024!")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
