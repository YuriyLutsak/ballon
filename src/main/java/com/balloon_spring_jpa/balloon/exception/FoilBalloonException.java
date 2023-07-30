package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class FoilBalloonException extends RuntimeException{

    public FoilBalloonException(UUID id) {
        super("there is no such foil balloon with this id: " + id);
    }
}
