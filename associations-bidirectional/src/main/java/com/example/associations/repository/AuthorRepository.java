package com.example.associations.repository;

import com.example.associations.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a from Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author fetchByName(String name);
}
