package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Test;


class PersonTest implements ModelTests {

  @Test
  void ungroupedAssertions() {

    Person p = new Person(1L, "Joe", "Buck");

    assertEquals(1L, p.getId());
    assertEquals("Joe", p.getFirstName());
    assertEquals("Buck", p.getLastName());

  }

  @Test
  void groupedAssertions() {

    Person p = new Person(1L, "Joe", "Buck");

    assertAll("Test Props Set", () -> assertEquals(1L, p.getId()),
        () -> assertEquals("Joe", p.getFirstName()), () -> assertEquals("Buck", p.getLastName()));

  }

  @Test
  void groupedAssertionsMsgs() {

    Person p = new Person(1L, "Joe", "Buck");

    assertAll("Test Props Set", () -> assertEquals(1L, p.getId(), "Id Failure"),
        () -> assertEquals("Joe", p.getFirstName(), "First Name Failure"),
        () -> assertEquals("Buck", p.getLastName(), "Last Name Failure"));

  }



}
