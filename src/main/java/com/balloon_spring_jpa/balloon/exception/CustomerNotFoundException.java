package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(UUID id) {
        super("Not existing Customer by id: " + id);
    }
}
