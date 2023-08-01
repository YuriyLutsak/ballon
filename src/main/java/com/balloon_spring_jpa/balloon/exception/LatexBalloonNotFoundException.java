package com.balloon_spring_jpa.balloon.exception;

import java.util.UUID;

public class LatexBalloonNotFoundException extends RuntimeException{

    public LatexBalloonNotFoundException(UUID id) {
        super("Not existing LatexBalloon by id: " + id);
    }
}
