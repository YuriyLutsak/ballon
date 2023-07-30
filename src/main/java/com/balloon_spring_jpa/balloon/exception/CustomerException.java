package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class CustomerException extends RuntimeException{

    public CustomerException(UUID id) {
        super("there is no such customer with this id: " + id);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
