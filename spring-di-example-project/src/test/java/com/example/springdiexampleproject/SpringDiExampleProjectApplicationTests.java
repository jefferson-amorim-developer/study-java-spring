package com.example.springdiexampleproject;

import com.example.springdiexampleproject.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringDiExampleProjectApplicationTests {

  @Autowired
  ApplicationContext ctx;

  @Autowired
  MyController myController;


  @Test
  void testAutowireOfController() {
    System.out.println(this.myController.sayHello());
  }


  @Test
  void testGetControllerFromCtx() {
    MyController myController = ctx.getBean(MyController.class);

    System.out.println(myController.sayHello());
  }

  @Test
  void contextLoads() {

  }

}
