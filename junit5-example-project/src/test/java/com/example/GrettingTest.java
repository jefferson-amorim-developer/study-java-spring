package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrettingTest {

  private Gretting gretting;

  @BeforeAll
  public static void beforeClass() {
    System.out.println("Before all...");
  }

  @BeforeEach
  void setUp() {
    System.out.println("Before each...");
    this.gretting = new Gretting();
  }

  @Test
  void testHelloWorld() {
    assertEquals("Hello World", this.gretting.helloWorld());
  }

  @Test
  void testHelloWorldWithParam() {
    assertEquals("Hello Paul", this.gretting.helloWorld("Paul"));
  }

  @AfterEach
  void tearDown() {
    System.out.println("After each...");
  }

  @AfterAll
  public static void afterClass() {
    System.out.println("After all...");
  }
}
