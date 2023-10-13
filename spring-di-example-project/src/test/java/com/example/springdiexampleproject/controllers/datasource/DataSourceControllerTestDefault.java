package com.example.springdiexampleproject.controllers.datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceControllerTestDefault {

  @Autowired
  DataSourceController controller;

  @Test
  void testConfig() {
    assertEquals("dev", this.controller.config());
  }
}
