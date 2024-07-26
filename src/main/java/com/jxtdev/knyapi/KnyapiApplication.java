package com.jxtdev.knyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class KnyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnyapiApplication.class, args);
	}

	License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    @Bean
    OpenAPI configSwagger(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Kimetsu No Yaiba - API REST")
                        .description("rest api created. As a teaching tool that allows you to learn more about the world and characters of Kimetsu no Yaiba, it will also help you develop your skills by practicing with it, which will boost you in the development of backend and frontend software. By consuming this API you will be able to interact with information. which you can use to create informative, practice or even game websites. Your mind is the one that gives the rules.")
                        .contact(new Contact()
                                .name("Jostin Banquez")
                                .email("jostinandresbanquez@gmail.com")
                                .url(""))
                        .version("1.0.2")
                        .license(mitLicense));
                        
    };
}
