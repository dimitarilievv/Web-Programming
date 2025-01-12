package mk.ukim.finki.wp2024.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long id) {
        super(String.format("Shopping cart with id %s was not found",id));
    }
}
