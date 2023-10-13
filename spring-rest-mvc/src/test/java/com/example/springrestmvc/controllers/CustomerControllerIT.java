package com.example.springrestmvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.springrestmvc.entities.Customer;
import com.example.springrestmvc.model.CustomerDTO;
import com.example.springrestmvc.repositories.CustomerRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerControllerIT {

  @Autowired
  CustomerController controller;

  @Autowired
  CustomerRepository repository;

  @Test
  void testListCustomers() {
    List<CustomerDTO> customers = controller.listAllCustomers();

    long totalCustomers = this.repository.count();

    assertThat(customers.size()).isEqualTo(totalCustomers);
  }

  @Test
  void testListCustomersEmpty() {
    repository.deleteAll();

    List<CustomerDTO> customers = controller.listAllCustomers();

    assertThat(customers.size()).isEqualTo(0);
  }

  @Test
  void testGetCustomerById() {
    Customer customer = repository.findAll().get(0);

    CustomerDTO dto = controller.getCustomerById(customer.getId());

    assertThat(dto).isNotNull();
  }


  @Test
  void testGetCustomerByIdNotFound() {
    UUID id = UUID.randomUUID();

    assertThrows(NotFoundException.class, () -> controller.getCustomerById(id));
  }

}
