package com.example.springrestmvc.services;

import com.example.springrestmvc.entities.Beer;
import com.example.springrestmvc.mappers.BeerMapper;
import com.example.springrestmvc.model.BeerDTO;
import com.example.springrestmvc.repositories.BeerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Primary
@Service
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

  private final BeerRepository repository;
  private final BeerMapper mapper;

  @Override
  public List<BeerDTO> listBeers() {
    return this.repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
  }

  @Override
  public Optional<BeerDTO> getBeerById(UUID id) {
    return this.repository.findById(id).map(this.mapper::entityToDto);
  }

  @Override
  public BeerDTO saveNewBeer(BeerDTO beer) {
    return this.mapper.entityToDto(this.repository.save(this.mapper.dtoToEntity(beer)));
  }

  @Override
  public Optional<BeerDTO> updateById(UUID id, BeerDTO beer) {
    AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

    this.repository.findById(id).ifPresentOrElse(existing -> {
      existing.setBeerName(beer.getBeerName());
      existing.setBeerStyle(beer.getBeerStyle());
      existing.setPrice(beer.getPrice());
      existing.setUpc(beer.getUpc());
      existing.setQuantityOnHand(beer.getQuantityOnHand());

      atomicReference.set(Optional.of(this.repository.save(existing)).map(mapper::entityToDto));
    }, () -> atomicReference.set(Optional.empty()));

    return atomicReference.get();
  }


  @Override
  public Boolean deleteById(UUID id) {
    if (this.repository.existsById(id)) {
      this.repository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public void patchBeerById(UUID id, BeerDTO beer) {
    Beer existing = this.repository.getReferenceById(id);
    boolean modified = false;

    if (StringUtils.hasText(beer.getBeerName())) {
      existing.setBeerName(beer.getBeerName());
      modified = true;
    }

    if (beer.getBeerStyle() != null) {
      existing.setBeerStyle(beer.getBeerStyle());
      modified = true;
    }

    if (beer.getPrice() != null) {
      existing.setPrice(beer.getPrice());
      modified = true;
    }

    if (beer.getQuantityOnHand() != null) {
      existing.setQuantityOnHand(beer.getQuantityOnHand());
      modified = true;
    }

    if (StringUtils.hasText(beer.getUpc())) {
      existing.setUpc(beer.getUpc());
      modified = true;
    }

    if (modified) {
      this.repository.save(existing);
    }
  }

}
