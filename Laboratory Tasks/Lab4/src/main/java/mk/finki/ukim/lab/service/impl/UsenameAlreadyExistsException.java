package mk.finki.ukim.lab.service.impl;

public class UsenameAlreadyExistsException extends RuntimeException{
    public UsenameAlreadyExistsException(String username) {
        super(String.format("User with username %s already exists",username));
    }
}
