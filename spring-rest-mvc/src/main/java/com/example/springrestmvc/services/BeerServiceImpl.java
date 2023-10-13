package com.example.springrestmvc.services;

import com.example.springrestmvc.model.BeerDTO;
import com.example.springrestmvc.model.BeerStyle;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

  private Map<UUID, BeerDTO> beerMap;

  public BeerServiceImpl() {
    this.beerMap = new HashMap<>();

    BeerDTO beer1 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Galaxy Cat")
        .beerStyle(BeerStyle.PALE_ALE).upc("12356").price(new BigDecimal("12.99"))
        .quantityOnHand(122).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now())
        .build();

    BeerDTO beer2 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Crank")
        .beerStyle(BeerStyle.PALE_ALE).upc("12356222").price(new BigDecimal("11.99"))
        .quantityOnHand(392).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now())
        .build();

    BeerDTO beer3 = BeerDTO.builder().id(UUID.randomUUID()).version(1).beerName("Sunshine City")
        .beerStyle(BeerStyle.IPA).upc("12356").price(new BigDecimal("13.99")).quantityOnHand(144)
        .createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

    beerMap.put(beer1.getId(), beer1);
    beerMap.put(beer2.getId(), beer2);
    beerMap.put(beer3.getId(), beer3);
  }

  @Override
  public List<BeerDTO> listBeers() {
    return new ArrayList<>(beerMap.values());
  }

  @Override
  public Optional<BeerDTO> getBeerById(UUID id) {

    log.debug("Get Beer by Id - in service. Id: " + id.toString());

    return Optional.of(beerMap.get(id));
  }

  @Override
  public BeerDTO saveNewBeer(BeerDTO beer) {
    BeerDTO savedBeer = BeerDTO.builder().id(UUID.randomUUID()).createdDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now()).version(1).beerName(beer.getBeerName()).upc(beer.getUpc())
        .price(beer.getPrice()).beerStyle(beer.getBeerStyle())
        .quantityOnHand(beer.getQuantityOnHand()).build();

    this.beerMap.put(savedBeer.getId(), savedBeer);

    return savedBeer;
  }

  @Override
  public Optional<BeerDTO> updateById(UUID id, BeerDTO beer) {
    BeerDTO existing = beerMap.get(id);

    existing.setBeerName(beer.getBeerName());
    existing.setBeerStyle(beer.getBeerStyle());
    existing.setPrice(beer.getPrice());
    existing.setQuantityOnHand(beer.getQuantityOnHand());
    existing.setUpc(beer.getUpc());

    existing.setVersion(existing.getVersion() + 1);
    existing.setUpdateDate(LocalDateTime.now());

    beerMap.put(existing.getId(), existing);

    return Optional.of(existing);
  }

  @Override
  public Boolean deleteById(UUID id) {
    this.beerMap.remove(id);
    return true;
  }

  @Override
  public void patchBeerById(UUID id, BeerDTO beer) {
    BeerDTO existing = this.beerMap.get(id);
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
      existing.setUpdateDate(LocalDateTime.now());
      existing.setVersion(existing.getVersion() + 1);

      this.beerMap.put(existing.getId(), existing);
    }
  }


}
