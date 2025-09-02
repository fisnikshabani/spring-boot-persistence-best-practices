package com.example.associationsunidirectional.service;

import com.example.associationsunidirectional.entity.Author;
import com.example.associationsunidirectional.entity.Book;
import com.example.associationsunidirectional.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookStoreService {

    private final AuthorRepository authorRepository;

    public BookStoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void insertAuthorWithBooks() {
        Author jn = new Author();
        jn.setName("Joana Nimar");
        jn.setAge(34);
        jn.setGenre("History");

        Book b1 = new Book();
        b1.setTitle("The Hunger Games");
        b1.setIsbn("978-0-306-40615-0");

        Book b2 = new Book();
        b2.setTitle("The Hunger Games: Catching Fire");
        b2.setIsbn("978-0-306-40616-0");

        Book b3 = new Book();
        b3.setTitle("The Hunger Games: Mockingjay - Part 1");
        b3.setIsbn("978-0-306-40617-0");

        jn.addBook(b1);
        jn.addBook(b2);
        jn.addBook(b3);

        authorRepository.save(jn);
    }

    @Transactional
    public void insertNewBook() {
        Author author = authorRepository.fetchByName("Joana Nimar");

        Book book = new Book();
        book.setTitle("The Hunger Games: Mockingjay - Part 2");
        book.setIsbn("978-0-306-40618-0");

        author.addBook(book);

        authorRepository.save(author);
    }

    @Transactional
    public void deleteLastBook() {

        Author author = authorRepository.fetchByName("Joana Nimar");
        List<Book> books = author.getBooks();
        author.removeBook(books.getLast());
    }

    public void deleteFirstBook() {

        Author author = authorRepository.fetchByName("Joana Nimar");
        List<Book> books = author.getBooks();
        author.removeBook(books.getFirst());
    }

}
