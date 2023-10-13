package com.example.springrestmvc.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.springrestmvc.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired
  CustomerRepository repository;

  @Test
  void testSaveCustomer() {
    Customer savedCustomer = repository.save(Customer.builder().name("New Customer").build());
    repository.flush();

    log.debug(savedCustomer.toString());

    assertThat(savedCustomer).isNotNull();
    assertThat(savedCustomer.getId()).isNotNull();
  }
}
