package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wpaud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wpaud.repository.InMemoryUserRepository;
import mk.ukim.finki.wpaud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final InMemoryUserRepository userRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        User user=new User(username, password, name, surname);
        userRepository.saveOrUpdate(user);
        return user;
    }
}
