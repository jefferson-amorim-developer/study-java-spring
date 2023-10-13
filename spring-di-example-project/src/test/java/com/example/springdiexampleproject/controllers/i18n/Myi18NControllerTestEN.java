package com.example.springdiexampleproject.controllers.i18n;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"dev", "EN"})
@SpringBootTest
public class Myi18NControllerTestEN {

  @Autowired
  Myi18NController myi18nController;

  @Test
  void sayHello() {

    System.out.println(this.myi18nController.sayHello());

  }


}
