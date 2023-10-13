package com.example.springrestmvc.controllers;

import com.example.springrestmvc.model.CustomerDTO;
import com.example.springrestmvc.services.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CustomerController {
  public static final String RESOURCE_PATH = "/api/v1/customer";
  public static final String RESOURCE_PATH_ID = RESOURCE_PATH + "/{customerId}";

  private final CustomerService customerService;

  @PatchMapping(RESOURCE_PATH_ID)
  public ResponseEntity<CustomerDTO> patchCustomerById(@PathVariable("customerId") UUID customerId,
      @RequestBody CustomerDTO customer) {

    customerService.patchCustomerById(customerId, customer);

    return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(RESOURCE_PATH_ID)
  public ResponseEntity<CustomerDTO> deleteCustomerById(
      @PathVariable("customerId") UUID customerId) {

    customerService.deleteCustomerById(customerId);

    return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(RESOURCE_PATH_ID)
  public ResponseEntity<CustomerDTO> updateCustomerByID(@PathVariable("customerId") UUID customerId,
      @RequestBody CustomerDTO customer) {

    customerService.updateCustomerById(customerId, customer);

    return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(RESOURCE_PATH)
  public ResponseEntity<CustomerDTO> handlePost(@RequestBody CustomerDTO customer) {
    CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", RESOURCE_PATH + "/" + savedCustomer.getId().toString());

    return new ResponseEntity<CustomerDTO>(headers, HttpStatus.CREATED);
  }

  @GetMapping(RESOURCE_PATH)
  public List<CustomerDTO> listAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping(value = RESOURCE_PATH_ID)
  public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) {
    return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
  }
}
