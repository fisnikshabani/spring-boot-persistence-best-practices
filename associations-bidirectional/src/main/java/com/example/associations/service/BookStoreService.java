package com.example.associations.service;

import com.example.associations.entity.Author;
import com.example.associations.entity.Book;
import com.example.associations.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService {

    private final AuthorRepository authorRepository;

    public BookStoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void insertAuthorWithBooks() {

        Author author = new Author();
        author.setName("Fisnik Shabani");
        author.setAge(38);
        author.setGenre("Sports");

        Book book = new Book();
        book.setTitle("The 5th Element");
        book.setIsbn("978-0-306-40615-0");

        author.addBook(book);

        authorRepository.save(author);
    }

    @Transactional
    public void deleteBookOfAuthor() {
        Author author = authorRepository.fetchByName("Fisnik Shabani");
        if (author != null && !author.getBooks().isEmpty()) {
            Book book = author.getBooks().get(0);
            System.out.println("Deleting book: " + book.getTitle() + " by " + author.getName());
            author.removeBook(book);
            System.out.println("Book deleted successfully");
        } else if (author != null && author.getBooks().isEmpty()) {
            System.out.println("Author '" + author.getName() + "' has no books to delete");
        } else {
            System.out.println("Author 'Fisnik Shabani' not found - no books to delete");
        }
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Author author = authorRepository.fetchByName("Fisnik Shabani");
        if (author != null && !author.getBooks().isEmpty()) {
            System.out.println("Deleting all " + author.getBooks().size() + " books for author: " + author.getName());
            author.removeBooks();
            System.out.println("All books deleted successfully");
        } else if (author != null && author.getBooks().isEmpty()) {
            System.out.println("Author '" + author.getName() + "' has no books to delete");
        } else {
            System.out.println("Author 'Fisnik Shabani' not found - no books to delete");
        }
    }
}
