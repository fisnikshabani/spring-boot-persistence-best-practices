package com.example.associationsunidirectional;

import com.example.associationsunidirectional.service.BookStoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssociationsUnidirectionalApplication {

    private final BookStoreService bookStoreService;

    public AssociationsUnidirectionalApplication(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    public static void main(String[] args) {
		SpringApplication.run(AssociationsUnidirectionalApplication.class, args);
	}

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\nInsert one author with three books  ...");
            bookStoreService.insertAuthorWithBooks();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nInsert new book to an author  ...");
            bookStoreService.insertNewBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete last book of an author  ...");
            bookStoreService.deleteLastBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete first book of an author  ...");
            bookStoreService.deleteFirstBook();
        };
    }

}
