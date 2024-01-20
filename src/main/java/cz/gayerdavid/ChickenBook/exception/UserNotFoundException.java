package cz.gayerdavid.ChickenBook.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("The user id '" + id + "' not found.");
    }
    
}
