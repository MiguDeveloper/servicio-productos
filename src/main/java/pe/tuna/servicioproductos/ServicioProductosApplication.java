package pe.tuna.servicioproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"pe.tuna.serviciocommons.models.entity"})
public class ServicioProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioProductosApplication.class, args);
    }

}