package com.crud.tasks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration //klasa w której będzie konfiguracja beanów albo projektu lub struktur
public class CoreConfiguration {



    @Bean //RestTemplate jest to klasa dostarczana przez Springa, która umożliwia realizowanie żądań HTTP pomiędzy serwerami.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
                .apis(RequestHandlerSelectors.any())// metody które mają być przeszukane w celu znalezienia controllerów
                .paths(PathSelectors.any())
                .build();

    }
}
