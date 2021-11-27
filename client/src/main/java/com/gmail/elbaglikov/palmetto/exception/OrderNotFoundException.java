package com.gmail.elbaglikov.palmetto.exception;

public class OrderNotFoundException extends IllegalArgumentException{
    private Long id;

    public OrderNotFoundException(Long id) {
        this.id = id;
    }

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String s) {
        super(s);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "No any order with id = " + id;
    }
}
