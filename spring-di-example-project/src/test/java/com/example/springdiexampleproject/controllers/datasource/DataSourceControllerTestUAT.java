package com.example.springdiexampleproject.controllers.datasource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"uat", "EN"})
@SpringBootTest
public class DataSourceControllerTestUAT {

  @Autowired
  DataSourceController controller;

  @Test
  void testConfig() {
    assertEquals("uat", this.controller.config());
  }
}
