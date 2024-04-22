package com.team1.progettocarsharingteam1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "OneMove API",
				version = "1.0",
				description = "Swagger documentation for our project OneMove"
		)
)
public class Progettocarsharingteam1Application {

    public static void main(String[] args) {
        SpringApplication.run(Progettocarsharingteam1Application.class, args);
    }
}