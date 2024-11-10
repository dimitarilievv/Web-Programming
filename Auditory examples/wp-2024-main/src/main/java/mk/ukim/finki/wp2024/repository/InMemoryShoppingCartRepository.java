package mk.ukim.finki.wp2024.repository;

import mk.ukim.finki.wp2024.bootstrap.DataHolder;
import mk.ukim.finki.wp2024.model.ShoppingCart;
import mk.ukim.finki.wp2024.model.enumeration.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    //optional secures that the returned value is not null
    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream()
                .filter(i->i.getId().equals(id)).findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream()
                .filter(I->I.getUser().getUsername().equals(username)&& I.getStatus().equals(status))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        DataHolder.shoppingCarts
                .removeIf(i->i.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }


}
