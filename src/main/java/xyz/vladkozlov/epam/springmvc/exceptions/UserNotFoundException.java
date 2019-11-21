package xyz.vladkozlov.epam.springmvc.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long userId) {
        super("User with id: " + userId + " not found!");
    }
}
