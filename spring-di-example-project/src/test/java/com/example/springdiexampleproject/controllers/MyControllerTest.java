package com.example.springdiexampleproject.controllers;

import org.junit.jupiter.api.Test;

public class MyControllerTest {


  @Test
  void testSayHello() {
    MyController myController = new MyController();

    System.out.println(myController.sayHello());
  }
}
