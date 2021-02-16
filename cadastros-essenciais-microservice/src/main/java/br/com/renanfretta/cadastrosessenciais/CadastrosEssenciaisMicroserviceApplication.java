package br.com.renanfretta.cadastrosessenciais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CadastrosEssenciaisMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrosEssenciaisMicroserviceApplication.class, args);
	}

}
