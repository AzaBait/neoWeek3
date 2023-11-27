package com.example.neobis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.neobis.entity")
@OpenAPIDefinition(servers = {
        @Server(url = "/", description = "Default Server URL"),
  //      @Server(url = "/myapp", description = "MyAPP Server URL")

})
public class NeobisApplication {


    public static void main(String[] args) {

        SpringApplication.run(NeobisApplication.class, args);
    }

}

