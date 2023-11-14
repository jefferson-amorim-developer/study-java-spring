package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import guru.springframework.sfgpetclinic.ControllerTests;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;


class IndexControllerTest implements ControllerTests {

  IndexController controller;

  @BeforeEach
  void setUp() {
    controller = new IndexController();
  }

  @DisplayName("Test Proper View name is returned for index page")
  @Test
  void testIndex() {
    assertEquals("index", controller.index());
    assertEquals("index", controller.index(), "Wrong View Returned");

    assertEquals("index", controller.index(), () -> "Another expensive message");

    assertThat(controller.index()).describedAs("Index failure").isEqualTo("index").hasSize(5);
  }

  @Test
  @DisplayName("Test exception")
  void testOupsHandler() {
    assertThrows(ValueNotFoundException.class, () -> {
      controller.oopsHandler();
    }, "Exception ");
  }

  @Disabled("Demo of timeout")
  @Test
  void testTimeout() {
    assertTimeout(Duration.ofMillis(100), () -> {
      Thread.sleep(2000);
      System.out.println("I got here");
    });
  }

  @Disabled("Demo of timeout")
  @Test
  void testTimeoutPreemptively() {
    assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      Thread.sleep(5000);
      System.out.println("I got here 123456789");
    });
  }

  @Test
  void testAssumptionTrue() {
    assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
  }

  @Test
  void testAssumptionTrueAssumptionIsTrue() {
    assumeTrue("GURU".equalsIgnoreCase("GURU"));
  }


  @EnabledOnOs(value = OS.MAC)
  @Test
  void testMeOnMacOs() {

  }

  @EnabledOnOs(value = OS.LINUX)
  @Test
  void testMeOnLinuxOs() {

  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  void testMeOnWindows() {

  }

  @EnabledOnJre(JRE.JAVA_8)
  @Test
  void testMeOnJava8() {

  }

  @EnabledOnJre(JRE.JAVA_11)
  @Test
  void testMeOnJava11() {

  }

  @EnabledOnJre(JRE.JAVA_17)
  @Test
  void testMeOnJava17() {

  }

  @EnabledOnJre(JRE.JAVA_21)
  @Test
  void testMeOnJava21() {

  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "jefferson")
  @Test
  void testIfUserJefferson() {

  }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
  @Test
  void testIfUserFred() {

  }
}
