package com.example.springrestmvc.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.springrestmvc.entities.Beer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
public class BeerRepositoryTest {

  @Autowired
  BeerRepository repository;

  @Test
  void testSaveBeer() {
    Beer savedBeer = repository.save(Beer.builder().beerName("My beer").build());
    repository.flush();

    log.debug(savedBeer.toString());

    assertThat(savedBeer).isNotNull();
    assertThat(savedBeer.getId()).isNotNull();
  }
}
