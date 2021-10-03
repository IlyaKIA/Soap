package com.geekbrains.spring.ws.services;

import com.geekbrains.spring.ws.entities.ProductEnt;
import com.geekbrains.spring.ws.repositories.ProductRepository;
import com.geekbrains.spring.ws.soap.products.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final Function<ProductEnt, Product> functionEntityToSoap = pe -> {
        Product p = new Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        p.setPicturePath(pe.getPicturePath());
        p.setCategoryTitle(pe.getCategory().getTitle());
        return p;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

    public Product getByTitle(String title) {
        return productRepository.findByTitle(title)
                .map(functionEntityToSoap)
                .get();
    }
}
