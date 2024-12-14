package mk.ukim.finki.wp2024.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "shop_users")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
//    private List<ShoppingCart> carts;
    public User() {

    }
}
