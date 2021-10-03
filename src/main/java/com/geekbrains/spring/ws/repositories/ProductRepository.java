package com.geekbrains.spring.ws.repositories;

import com.geekbrains.spring.ws.entities.ProductEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEnt, Long> {
    @Query("select s from ProductEnt s where s.title = ?1")
    Optional<ProductEnt> findByTitle(String title);
}
