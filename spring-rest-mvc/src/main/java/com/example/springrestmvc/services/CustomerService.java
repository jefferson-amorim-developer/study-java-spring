package com.example.springrestmvc.services;

import com.example.springrestmvc.model.CustomerDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

  Optional<CustomerDTO> getCustomerById(UUID uuid);

  List<CustomerDTO> getAllCustomers();

  CustomerDTO saveNewCustomer(CustomerDTO customer);

  void updateCustomerById(UUID customerId, CustomerDTO customer);

  void deleteCustomerById(UUID customerId);

  void patchCustomerById(UUID customerId, CustomerDTO customer);
}
