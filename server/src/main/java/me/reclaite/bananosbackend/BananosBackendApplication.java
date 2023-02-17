package me.reclaite.bananosbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BananosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BananosBackendApplication.class, args);
	}

}
