package mk.ukim.finki.wp2024.service.impl;

import mk.ukim.finki.wp2024.model.Role;
import mk.ukim.finki.wp2024.model.User;
import mk.ukim.finki.wp2024.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp2024.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp2024.model.exceptions.UsenameAlreadyExistsException;
import mk.ukim.finki.wp2024.repository.impl.jpa.UserRepository;
import mk.ukim.finki.wp2024.service.UserService;
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
    public User register(String username, String password, String repeatPassword, String name, String surname,Role role) {
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
        User user=new User(username, passwordEncoder.encode(password), name, surname,role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
