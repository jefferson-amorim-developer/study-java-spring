package guru.springframework.sfgpetclinic.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OwnerTest implements ModelTests {


  @Test
  void dependentAssertions() {

    Owner owner = new Owner(1L, "Joe", "Buck");
    owner.setCity("Key West");
    owner.setTelephone("1231231234");

    assertAll("Properties Test",
        () -> assertAll("Person Properties",
            () -> assertEquals("Joe", owner.getFirstName(), "First Name Failure"),
            () -> assertEquals("Buck", owner.getLastName(), "Last Name Failure")),
        () -> assertAll("Owner Properties",
            () -> assertEquals("Key West", owner.getCity(), "City Failure"),
            () -> assertEquals("1231231234", owner.getTelephone(), "Telephone Failure")));

    assertThat(owner.getCity(), is("Key West"));
  }

  @DisplayName("Value Source Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @ValueSource(strings = {"Spring", "Framework", "Guru"})
  void testValueSource(String val) {
    System.out.println(val);
  }

  @DisplayName("Enum Source Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @EnumSource(OwnerType.class)
  void testEnumSource(OwnerType type) {
    System.out.println(type);
  }


  @DisplayName("CSV Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvSource({"FL, 1, 1", "OH, 2, 2", "MI, 3, 3",})
  void testCSVInput(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + ":" + value2);
  }

  @DisplayName("CSV Input File Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
  void testCSVInputFile(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + ":" + value2);
  }


  @DisplayName("Method Provider Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @MethodSource("getargs")
  void testMethodProvider(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + ":" + value2);
  }

  static Stream<Arguments> getargs() {
    return Stream.of(Arguments.of("FL", 1, 1), Arguments.of("OH", 2, 2), Arguments.of("MI", 5, 4));
  }

  @DisplayName("Custom Provider Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @ArgumentsSource(CustomArgsProvider.class)
  void testCustomProvider(String stateName, int value1, int value2) {
    System.out.println(stateName + " = " + value1 + ":" + value2);
  }

}
