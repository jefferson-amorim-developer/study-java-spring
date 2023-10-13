package com.example.springrestmvc.bootstrap;

import com.example.springrestmvc.entities.Beer;
import com.example.springrestmvc.entities.Customer;
import com.example.springrestmvc.model.BeerStyle;
import com.example.springrestmvc.repositories.BeerRepository;
import com.example.springrestmvc.repositories.CustomerRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

  private final BeerRepository beerRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void run(String... args) throws Exception {

    loadBeerData();
    loadCustomerData();
  }

  private void loadCustomerData() {

    if (this.customerRepository.count() == 0) {
      Customer customer1 = Customer.builder().name("Customer 1").createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();

      Customer customer2 = Customer.builder().name("Customer 2").createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();

      Customer customer3 = Customer.builder().name("Customer 3").createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();


      this.customerRepository.save(customer1);
      this.customerRepository.save(customer2);
      this.customerRepository.save(customer3);
    }
  }

  private void loadBeerData() {
    if (this.beerRepository.count() == 0) {
      Beer beer1 = Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyle.PALE_ALE).upc("12356")
          .price(new BigDecimal("12.99")).quantityOnHand(122).createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();

      Beer beer2 = Beer.builder().beerName("Crank").beerStyle(BeerStyle.PALE_ALE).upc("12356222")
          .price(new BigDecimal("11.99")).quantityOnHand(392).createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();

      Beer beer3 = Beer.builder().beerName("Sunshine City").beerStyle(BeerStyle.IPA).upc("12356")
          .price(new BigDecimal("13.99")).quantityOnHand(144).createdDate(LocalDateTime.now())
          .updateDate(LocalDateTime.now()).build();

      this.beerRepository.save(beer1);
      this.beerRepository.save(beer2);
      this.beerRepository.save(beer3);
    }
  }



}
