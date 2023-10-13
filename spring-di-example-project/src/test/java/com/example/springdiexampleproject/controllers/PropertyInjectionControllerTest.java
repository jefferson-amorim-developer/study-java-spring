package com.example.springdiexampleproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertyInjectionControllerTest {


  @Autowired
  PropertyInjectionController propertyInjectionController;

  // @BeforeEach
  // void setUp() {
  // propertyInjectionController = new PropertyInjectionController();
  // propertyInjectionController.greetingService = new GreetingServiceImpl();

  // }

  @Test
  void testSayHello() {
    System.out.println(this.propertyInjectionController.sayHello());
  }
}
