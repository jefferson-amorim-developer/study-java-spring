package com.example.springwebapp.services;

import com.example.springwebapp.domain.Author;
import com.example.springwebapp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Iterable<Author> findAll() {
    return this.authorRepository.findAll();
  }

}
