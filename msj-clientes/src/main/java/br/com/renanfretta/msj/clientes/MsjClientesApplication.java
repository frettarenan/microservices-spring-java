package br.com.renanfretta.msj.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsjClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsjClientesApplication.class, args);
	}

}
