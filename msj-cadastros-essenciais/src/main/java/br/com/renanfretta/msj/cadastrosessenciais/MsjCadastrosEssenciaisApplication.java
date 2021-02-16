package br.com.renanfretta.msj.cadastrosessenciais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsjCadastrosEssenciaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsjCadastrosEssenciaisApplication.class, args);
	}

}
