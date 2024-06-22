package br.com.univille.germoneys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GermoneysApplication {

	public static void main(String[] args) {
		SpringApplication.run(GermoneysApplication.class, args);

		System.out.println("Germoneys rodando! Acesse: http://localhost:8080/");
	}
}
