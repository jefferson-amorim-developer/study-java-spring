package com.example.springrestmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringRestMvcApplication {

  // @Bean
  // public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
  // return new Jackson2ObjectMapperBuilder()
  // // .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("dd/MM/yyyy
  // // HH:mm")))
  // .serializationInclusion(JsonInclude.Include.NON_NULL);
  // }

  public static void main(String[] args) {
    SpringApplication.run(SpringRestMvcApplication.class, args);



  }

}
