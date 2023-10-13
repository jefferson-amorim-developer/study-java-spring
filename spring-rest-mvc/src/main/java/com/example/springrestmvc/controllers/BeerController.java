package com.example.springrestmvc.controllers;

import com.example.springrestmvc.model.BeerDTO;
import com.example.springrestmvc.services.BeerService;
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
public class BeerController {

  public static final String RESOURCE_PATH = "/api/v1/beer";
  public static final String RESOURCE_PATH_ID = "/api/v1/beer/{id}";

  private final BeerService service;

  @PatchMapping(RESOURCE_PATH_ID)
  public ResponseEntity<Void> updateBeerPatchById(@PathVariable(name = "id") UUID id,
      @RequestBody BeerDTO beer) {
    this.service.patchBeerById(id, beer);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }


  @DeleteMapping(RESOURCE_PATH_ID)
  public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
    if (!this.service.deleteById(id)) {
      throw new NotFoundException();
    }
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(RESOURCE_PATH_ID)
  public ResponseEntity<Void> updateById(@PathVariable(name = "id") UUID id,
      @RequestBody BeerDTO beer) {
    this.service.updateById(id, beer).orElseThrow(NotFoundException::new);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(RESOURCE_PATH)
  public ResponseEntity<BeerDTO> handlePost(@RequestBody BeerDTO beer) {
    BeerDTO savedBeer = service.saveNewBeer(beer);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.LOCATION, RESOURCE_PATH + "/" + savedBeer.getId().toString());

    return new ResponseEntity<BeerDTO>(savedBeer, headers, HttpStatus.CREATED);
  }


  @GetMapping(RESOURCE_PATH)
  public List<BeerDTO> listBeers() {
    return service.listBeers();
  }

  @GetMapping(RESOURCE_PATH_ID)
  public BeerDTO getBeerById(@PathVariable(name = "id") UUID id) {
    return service.getBeerById(id).orElseThrow(NotFoundException::new);
  }

  // @ExceptionHandler(NotFoundException.class)
  // public ResponseEntity<Beer> handleNotFoundException() {
  // return ResponseEntity.notFound().build();
  // }



}


