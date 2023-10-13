package com.example.springdiexampleproject.controllers.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class Myi18NControllerTestDefault {

  @Autowired
  Myi18NController myi18nController;

  @Test
  void sayHello() {

    System.out.println(this.myi18nController.sayHello());

  }


}
