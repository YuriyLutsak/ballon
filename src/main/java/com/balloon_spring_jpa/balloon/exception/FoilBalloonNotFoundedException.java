package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class FoilBalloonNotFoundedException extends RuntimeException{

    public FoilBalloonNotFoundedException(UUID id) {
        super("Not existing FoilBalloon by id: " + id);
    }
}
