package cz.gayerdavid.ChickenBook.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("The post id '" + id + "' not found.");
    }

}
