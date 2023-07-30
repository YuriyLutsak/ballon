package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class OrderException extends RuntimeException{

    public OrderException(UUID id) {
        super("there is no such order with this id: " + id);
    }
}
