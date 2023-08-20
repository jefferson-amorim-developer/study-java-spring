package com.example.springwebapp.services;

import com.example.springwebapp.domain.Book;

public interface BookService {
  Iterable<Book> findAll();
}
