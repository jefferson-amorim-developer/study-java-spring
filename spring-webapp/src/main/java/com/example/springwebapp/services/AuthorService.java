package com.example.springwebapp.services;

import com.example.springwebapp.domain.Author;

public interface AuthorService {
  public Iterable<Author> findAll();

}
