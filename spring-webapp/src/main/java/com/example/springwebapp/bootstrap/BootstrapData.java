package com.example.springwebapp.bootstrap;

import com.example.springwebapp.domain.Author;
import com.example.springwebapp.domain.Book;
import com.example.springwebapp.domain.Publisher;
import com.example.springwebapp.repositories.AuthorRepository;
import com.example.springwebapp.repositories.BookRepository;
import com.example.springwebapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;



  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Author eric = new Author();
    eric.setFirstName("Eric");
    eric.setLastName("Evans");

    Book ddd = new Book();
    ddd.setTitle("Domain Driven Design");
    ddd.setIsbn("123456");

    Author ericSaved = authorRepository.save(eric);
    Book dddSaved = bookRepository.save(ddd);

    Author rod = new Author();
    rod.setFirstName("Rod");
    rod.setLastName("Johnson");

    Book noEJB = new Book();
    noEJB.setTitle("J2EE Development without EJB");
    noEJB.setIsbn("54757585");

    Author rodSaved = authorRepository.save(rod);
    Book noEJBSaved = bookRepository.save(noEJB);

    dddSaved.getAuthors().add(ericSaved);
    ericSaved.getBooks().add(dddSaved);

    noEJBSaved.getAuthors().add(rodSaved);
    rodSaved.getBooks().add(noEJBSaved);

    Publisher publisher1 = new Publisher();
    publisher1.setName("Publisher 01");
    publisher1.setAddress("Address of publisher 01");
    publisher1.setCity("City 01");
    publisher1.setState("State 01");
    publisher1.setZip("6644558877");
    publisherRepository.save(publisher1);

    dddSaved.setPublisher(publisher1);
    noEJBSaved.setPublisher(publisher1);

    authorRepository.save(ericSaved);
    authorRepository.save(rodSaved);
    bookRepository.save(dddSaved);
    bookRepository.save(noEJBSaved);

    System.out.println("In Bootstrap");
    System.out.println("Author Count: " + authorRepository.count());
    System.out.println("Book Count: " + bookRepository.count());
    System.out.println("Publisher Count: " + publisherRepository.count());
  }

}
