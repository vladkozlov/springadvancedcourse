package xyz.vladkozlov.epam.springmvc.exceptions;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String username) {
        super("User with username: " + username + " already exist!");
    }
}
