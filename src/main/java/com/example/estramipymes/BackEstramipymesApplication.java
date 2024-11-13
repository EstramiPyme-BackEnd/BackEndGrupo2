package com.example.estramipymes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackEstramipymesApplication {

	public static void main(String[] args) {
		// Cargar las variables de entorno desde el archivo .env
        Dotenv dotenv = Dotenv.load();

        // Establecer las variables de entorno en el sistema
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
        System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
		System.setProperty("DB_NAME", dotenv.get("DB_NAME"));

		SpringApplication.run(BackEstramipymesApplication.class, args);
	}

}
