package mk.ukim.finki.wp2024.service.impl;

import mk.ukim.finki.wp2024.model.Product;
import mk.ukim.finki.wp2024.model.ShoppingCart;
import mk.ukim.finki.wp2024.model.User;
import mk.ukim.finki.wp2024.model.enumeration.ShoppingCartStatus;
import mk.ukim.finki.wp2024.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wp2024.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp2024.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp2024.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp2024.repository.InMemoryShoppingCartRepository;
import mk.ukim.finki.wp2024.repository.InMemoryUserRepository;
import mk.ukim.finki.wp2024.service.ProductService;
import mk.ukim.finki.wp2024.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository, InMemoryUserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (this.shoppingCartRepository.findById(cartId).isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository
                            .findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (!shoppingCart.getProducts()
                .stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).isEmpty()) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}