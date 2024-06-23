package br.com.univille.germoneys.config;

import br.com.univille.germoneys.security.jwt.JwtTokenManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Profile("dev")
public class DevAuthConfig {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @PostConstruct
    public void generateDevToken() {
        Authentication auth = new UsernamePasswordAuthenticationToken("admin@germoneys.com", null, new ArrayList<>());
        String token = jwtTokenManager.generateToken(auth);

        System.out.println();
        System.out.println("########################################################################### Dev JWT token ######################################################################################################");
        System.out.println(token);
        System.out.println("################################################################################################################################################################################################");
        System.out.println();
    }
}