package com.geekbrains.spring.ws.repositories;

import com.geekbrains.spring.ws.entities.CategoryEnt;
import com.geekbrains.spring.ws.soap.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEnt, Long> {
    @Query("select g from CategoryEnt g where g.title = ?1")
    Optional<CategoryEnt> findByTitle(String title);
}
