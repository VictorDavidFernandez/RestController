package com.example.GorkaMalvado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.example.GorkaMalvado.*" })
@SpringBootApplication
public class GorkaMalvadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GorkaMalvadoApplication.class, args);
	}

}
