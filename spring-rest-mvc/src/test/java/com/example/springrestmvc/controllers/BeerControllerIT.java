package com.example.springrestmvc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.springrestmvc.entities.Beer;
import com.example.springrestmvc.mappers.BeerMapper;
import com.example.springrestmvc.model.BeerDTO;
import com.example.springrestmvc.repositories.BeerRepository;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BeerControllerIT {
  @Autowired
  BeerController controller;

  @Autowired
  BeerRepository repository;

  @Autowired
  BeerMapper mapper;


  @Rollback
  @Transactional
  @Test
  void testDeleteByIdFound() {
    Beer beer = repository.findAll().get(0);
    ResponseEntity<Void> response = controller.deleteById(beer.getId());

    repository.flush();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    assertThat(repository.findById(beer.getId())).isEmpty();
  }

  @Test
  void testDeleteByIdNotFound() {
    assertThrows(NotFoundException.class, () -> {
      controller.deleteById(UUID.randomUUID());
    });
  }



  @Test
  void testUpdateNotFound() {
    assertThrows(NotFoundException.class, () -> {
      controller.updateById(UUID.randomUUID(), BeerDTO.builder().build());
    });
  }

  @Rollback
  @Transactional
  @Test
  void testUpdateBeer() {

    Beer beer = repository.findAll().get(0);
    BeerDTO dto = mapper.entityToDto(beer);

    dto.setId(null);
    dto.setVersion(null);
    final String beerName = "Updated Beer";
    dto.setBeerName(beerName);

    ResponseEntity<Void> response = controller.updateById(beer.getId(), dto);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    Beer updatedBeer = repository.findById(beer.getId()).get();

    assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);



  }

  @Rollback
  @Transactional
  @Test
  void testSaveNewBeer() {
    BeerDTO dto = BeerDTO.builder().beerName("New Beer").build();

    ResponseEntity<BeerDTO> response = controller.handlePost(dto);

    URI location = response.getHeaders().getLocation();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(location).isNotNull();

    String[] pathSegments = location.getPath().split("/");
    UUID savedUUID = UUID.fromString(pathSegments[pathSegments.length - 1]);

    Beer beer = repository.findById(savedUUID).get();
    assertThat(beer).isNotNull();
  }

  @Test
  void testGetById() {
    Beer beer = repository.findAll().get(0);

    BeerDTO dto = controller.getBeerById(beer.getId());

    assertThat(dto).isNotNull();
  }

  @Test
  void testBeerIdNotFound() {
    UUID id = UUID.randomUUID();

    assertThrows(NotFoundException.class, () -> controller.getBeerById(id));
  }

  @Test
  void testListBeers() {
    List<BeerDTO> dtos = controller.listBeers();

    assertThat(dtos.size()).isEqualTo(3);
  }

  @Rollback
  @Transactional
  @Test
  void testEmptyList() {
    repository.deleteAll();
    List<BeerDTO> dtos = controller.listBeers();

    assertThat(dtos.size()).isEqualTo(0);
  }
}
