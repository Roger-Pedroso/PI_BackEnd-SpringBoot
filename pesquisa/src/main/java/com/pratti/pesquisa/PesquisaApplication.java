package com.pratti.pesquisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//exclude = SecurityAutoConfiguration.class
public class PesquisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PesquisaApplication.class, args);
	}

}
