package br.com.univille.germoneys;

import br.com.univille.germoneys.entity.User;
import br.com.univille.germoneys.service.user.UserService;
import br.com.univille.germoneys.service.user.dto.UserCreationDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GermoneysApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GermoneysApplication.class, args);
	}

	@PostConstruct
	public void init() {
		createAdminUserIfNecessary(userService);
		System.out.println("Germoneys rodando! Acesse: http://localhost:8080/swagger-ui/index.html");
	}

	private void createAdminUserIfNecessary(UserService userService) {
		String adminEmail = "admin@germoneys.com";

		User existingAdmin = userService.findByEmail(adminEmail);

		if (existingAdmin != null) return;

		UserCreationDto adminUser = new UserCreationDto();
		adminUser.setEmail(adminEmail);
		adminUser.setPassword("GermoneysAdmin22062024!");
		adminUser.setName("Admin Germoneys");

		userService.create(adminUser);
	}
}
