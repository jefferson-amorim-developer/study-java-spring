package com.example.springrestmvc.mappers;

import com.example.springrestmvc.entities.Beer;
import com.example.springrestmvc.model.BeerDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

  Beer dtoToEntity(BeerDTO dto);

  BeerDTO entityToDto(Beer entity);

  List<Beer> dtoListToEntityList(List<BeerDTO> list);

  List<BeerDTO> entityListToDtoList(List<Beer> list);

}
