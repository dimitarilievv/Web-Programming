package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.User;
import mk.finki.ukim.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.lab.repository.jpa.UserRepository;
import mk.finki.ukim.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        // Check if the username, password, name and surname are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        // Check if the password and the repeated password are the same
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsenameAlreadyExistsException(username);
        }
        User user=new User(username, passwordEncoder.encode(password), name, surname);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        // Check if the username and password are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(InvalidUserCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }
        return user;
    }

}
