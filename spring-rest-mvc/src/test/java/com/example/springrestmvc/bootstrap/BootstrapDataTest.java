package com.example.springrestmvc.bootstrap;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.springrestmvc.repositories.BeerRepository;
import com.example.springrestmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BootstrapDataTest {

  @Autowired
  BeerRepository beerRepository;

  @Autowired
  CustomerRepository customerRepository;

  BootstrapData bootstrapData;

  @BeforeEach
  void setUp() {
    this.bootstrapData = new BootstrapData(beerRepository, customerRepository);
  }

  @Test
  void testRun() throws Exception {

    this.bootstrapData.run();

    assertThat(this.beerRepository.count()).isEqualTo(3);
    assertThat(this.customerRepository.count()).isEqualTo(3);

  }
}
