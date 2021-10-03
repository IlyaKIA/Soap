package com.geekbrains.spring.ws.services;

import com.geekbrains.spring.ws.entities.CategoryEnt;
import com.geekbrains.spring.ws.repositories.CategoryRepository;
import com.geekbrains.spring.ws.soap.categories.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public static final Function<CategoryEnt, Category> functionEntityToSoap = ge -> {
        Category g = new Category();
        g.setTitle(g.getTitle());
        ge.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(s -> g.getProducts().add(s));
        return g;
    };

    public Category getByTitle(String title) {
        return categoryRepository.findByTitle(title).map(functionEntityToSoap).get();
    }
}
