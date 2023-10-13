package com.example.springrestmvc.services;

import com.example.springrestmvc.entities.Customer;
import com.example.springrestmvc.mappers.CustomerMapper;
import com.example.springrestmvc.model.CustomerDTO;
import com.example.springrestmvc.repositories.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Primary
@Service
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  @Override
  public Optional<CustomerDTO> getCustomerById(UUID uuid) {
    return this.repository.findById(uuid).map(this.mapper::entityToDto);
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return this.repository.findAll().stream().map(this.mapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO saveNewCustomer(CustomerDTO customer) {
    return this.mapper.entityToDto(this.repository.save(this.mapper.dtoToEntity(customer)));
  }

  @Override
  public void updateCustomerById(UUID customerId, CustomerDTO customer) {
    Customer existing = this.repository.getReferenceById(customerId);

    existing.setName(customer.getName());

    this.repository.save(existing);
  }

  @Override
  public void deleteCustomerById(UUID customerId) {
    this.repository.deleteById(customerId);
  }

  @Override
  public void patchCustomerById(UUID customerId, CustomerDTO customer) {
    Customer existing = this.repository.getReferenceById(customerId);
    boolean modified = false;

    if (StringUtils.hasText(customer.getName())) {
      existing.setName(customer.getName());
      modified = true;
    }

    if (modified) {
      this.repository.save(existing);
    }
  }

}
