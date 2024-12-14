package mk.ukim.finki.wp2024.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp2024.model.enumeration.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCreated;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {

    }


    public ShoppingCart(User user) {
        this.dataCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
