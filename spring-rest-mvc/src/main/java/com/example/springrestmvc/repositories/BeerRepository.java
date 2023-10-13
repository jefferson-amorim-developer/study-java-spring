package com.example.springrestmvc.repositories;

import com.example.springrestmvc.entities.Beer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}
