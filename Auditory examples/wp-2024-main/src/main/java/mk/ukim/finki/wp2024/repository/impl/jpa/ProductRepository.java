package mk.ukim.finki.wp2024.repository.impl.jpa;

import mk.ukim.finki.wp2024.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    void deleteByName(String name);
}
