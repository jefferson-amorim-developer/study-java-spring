package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled(value = "Disabled until we learn Mocking")
@Tag("services")
public class OwnerSDJpaServiceTest {

  OwnerSDJpaService service;

  @BeforeEach
  void setUp() {
    service = new OwnerSDJpaService(null, null, null);
  }

  @Disabled
  @Test
  void testFindByLastName() {

    Owner foundOwner = service.findByLastName("Buck");

  }

  @Test
  void testFindAllByLastNameLike() {

  }

  @Test
  void testFindAll() {

  }

  @Test
  void testFindById() {

  }

  @Test
  void testSave() {

  }

  @Test
  void testDelete() {

  }

  @Test
  void testDeleteById() {

  }

}
