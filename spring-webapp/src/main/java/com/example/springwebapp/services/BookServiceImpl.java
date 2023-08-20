package com.example.springwebapp.services;

import com.example.springwebapp.domain.Book;
import com.example.springwebapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Iterable<Book> findAll() {
    return this.bookRepository.findAll();
  }

}
