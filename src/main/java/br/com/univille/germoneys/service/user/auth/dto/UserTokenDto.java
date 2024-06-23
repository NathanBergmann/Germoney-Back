package br.com.univille.germoneys.service.user.auth.dto;

import lombok.Data;

@Data
public class UserTokenDto {

    private Long userId;

    private String name;

    private String email;

    private String token;
}
