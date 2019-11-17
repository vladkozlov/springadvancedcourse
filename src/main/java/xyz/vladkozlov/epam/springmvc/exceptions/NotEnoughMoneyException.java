package xyz.vladkozlov.epam.springmvc.exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super("Not enough money on account");
    }
}
