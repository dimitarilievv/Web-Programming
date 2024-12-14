package mk.ukim.finki.wp2024.model.exceptions;

public class UsenameAlreadyExistsException extends RuntimeException{
    public UsenameAlreadyExistsException(String username) {
        super(String.format("User with username %s already exists",username));
    }
}
