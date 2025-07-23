package com.example.associations;

import com.example.associations.service.BookStoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssociationsApplication {

    private final BookStoreService bookStoreService;

    public AssociationsApplication(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AssociationsApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nInsert author with books");
            bookStoreService.insertAuthorWithBooks();

            System.out.println("\nDelete a book of an author");
            bookStoreService.deleteBookOfAuthor();

            System.out.println("\nDelete all books of an author");
            bookStoreService.deleteAllBooksOfAuthor();
        };
    }

}
