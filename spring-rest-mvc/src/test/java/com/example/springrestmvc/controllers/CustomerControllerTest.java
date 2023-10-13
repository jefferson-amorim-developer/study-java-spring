package com.example.springrestmvc.controllers;

import static com.example.springrestmvc.controllers.CustomerController.RESOURCE_PATH;
import static com.example.springrestmvc.controllers.CustomerController.RESOURCE_PATH_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.springrestmvc.model.CustomerDTO;
import com.example.springrestmvc.services.CustomerService;
import com.example.springrestmvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @MockBean
  CustomerService customerService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  CustomerServiceImpl customerServiceImpl;

  @BeforeEach
  void setUp() {
    customerServiceImpl = new CustomerServiceImpl();
  }

  @Captor
  ArgumentCaptor<UUID> uuidArgumentCaptor;

  @Captor
  ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

  @Test
  void testPatchCustomer() throws Exception {
    CustomerDTO customer = customerServiceImpl.getAllCustomers().get(0);

    Map<String, Object> customerMap = new HashMap<>();
    customerMap.put("name", "New Name");

    mockMvc
        .perform(patch(RESOURCE_PATH_ID, customer.getId()).contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerMap)))
        .andExpect(status().isNoContent());

    verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(),
        customerArgumentCaptor.capture());

    assertThat(uuidArgumentCaptor.getValue()).isEqualTo(customer.getId());
    assertThat(customerArgumentCaptor.getValue().getName()).isEqualTo(customerMap.get("name"));
  }

  @Test
  void testDeleteCustomer() throws Exception {
    CustomerDTO customer = customerServiceImpl.getAllCustomers().get(0);

    mockMvc.perform(delete(RESOURCE_PATH_ID, customer.getId()).contentType(APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());

    assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
  }

  @Test
  void testUpdateCustomer() throws Exception {
    CustomerDTO customer = customerServiceImpl.getAllCustomers().get(0);

    mockMvc.perform(
        put(RESOURCE_PATH_ID, customer.getId()).content(objectMapper.writeValueAsString(customer))
            .contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(customerService).updateCustomerById(uuidArgumentCaptor.capture(),
        any(CustomerDTO.class));

    assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
  }

  @Test
  void testCreateCustomer() throws Exception {
    CustomerDTO customer = customerServiceImpl.getAllCustomers().get(0);
    customer.setId(null);
    customer.setVersion(null);

    given(customerService.saveNewCustomer(any(CustomerDTO.class)))
        .willReturn(customerServiceImpl.getAllCustomers().get(1));

    mockMvc
        .perform(post(RESOURCE_PATH).contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)))
        .andExpect(status().isCreated()).andExpect(header().exists("Location"));
  }

  @Test
  void listAllCustomers() throws Exception {
    given(customerService.getAllCustomers()).willReturn(customerServiceImpl.getAllCustomers());

    mockMvc.perform(get(RESOURCE_PATH).accept(APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(3)));
  }

  @Test
  void getCustomerByIdNotFound() throws Exception {

    given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

    mockMvc.perform(get(RESOURCE_PATH_ID, UUID.randomUUID())).andExpect(status().isNotFound());
  }

  @Test
  void getCustomerById() throws Exception {
    CustomerDTO customer = customerServiceImpl.getAllCustomers().get(0);

    given(customerService.getCustomerById(customer.getId())).willReturn(Optional.of(customer));

    mockMvc.perform(get(RESOURCE_PATH_ID, customer.getId()).accept(APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.name", is(customer.getName())));
  }
}
