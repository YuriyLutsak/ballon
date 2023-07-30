package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class LatexBalloonException extends RuntimeException{

    public LatexBalloonException(UUID id) {
        super("there is no such latex balloon with this id: " + id);
    }
}
