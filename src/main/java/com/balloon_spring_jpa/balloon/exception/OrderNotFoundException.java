package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(UUID id) {
        super("Not existing Order by id:" + id);
    }
}
