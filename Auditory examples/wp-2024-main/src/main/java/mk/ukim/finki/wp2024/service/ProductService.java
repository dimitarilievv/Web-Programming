package mk.ukim.finki.wp2024.service;

import mk.ukim.finki.wp2024.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price,
                           Integer quantity, Long category, Long manufacturer);
    void deleteById(Long id);
}

