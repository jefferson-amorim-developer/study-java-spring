package com.example.springrestmvc.services;

import com.example.springrestmvc.model.BeerDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BeerService {

  List<BeerDTO> listBeers();

  Optional<BeerDTO> getBeerById(UUID id);

  BeerDTO saveNewBeer(BeerDTO beer);

  Optional<BeerDTO> updateById(UUID id, BeerDTO beer);

  Boolean deleteById(UUID id);

  void patchBeerById(UUID id, BeerDTO beer);

}
