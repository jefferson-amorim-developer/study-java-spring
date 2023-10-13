package com.example.springdiexampleproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetterInjectedControllerTest {

  @Autowired
  SetterInjectedController setterInjectedController;

  // @BeforeEach
  // void setUp() {

  // this.setterInjectedController = new SetterInjectedController();
  // this.setterInjectedController.setGreetingService(new GreetingServiceImpl());

  // }

  @Test
  void testSayHello() {
    System.out.println(this.setterInjectedController.sayHello());

  }
}
