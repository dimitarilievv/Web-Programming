package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User register(String username, String password, String repeatPassword,
                  String name, String surname);
    User login(String username, String password);
}
