package mk.ukim.finki.wp2024.repository.impl.jpa;

import mk.ukim.finki.wp2024.model.ShoppingCart;
import mk.ukim.finki.wp2024.model.User;
import mk.ukim.finki.wp2024.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.wp2024.service.ShoppingCartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
