package com.example.springdiexampleproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConstructorInjectedControllerTest {

  @Autowired
  ConstructorInjectedController controller;

  // @BeforeEach
  // void setUp() {
  // controller = new ConstructorInjectedController(new GreetingServiceImpl());
  // }


  @Test
  void testSayHello() {
    System.out.println(this.controller.sayHello());
  }
}
