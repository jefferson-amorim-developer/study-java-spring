package com.example.springrestmvc.mappers;

import com.example.springrestmvc.entities.Customer;
import com.example.springrestmvc.model.CustomerDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {


  Customer dtoToEntity(CustomerDTO dto);

  CustomerDTO entityToDto(Customer entity);

  List<Customer> dtoListToEntityList(List<CustomerDTO> list);

  List<CustomerDTO> entityListToDtoList(List<Customer> list);

}
