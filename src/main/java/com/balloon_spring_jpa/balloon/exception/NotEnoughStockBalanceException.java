package com.balloon_spring_jpa.balloon.exception;

import java.util.*;

public class NotEnoughStockBalanceException extends RuntimeException{

    public NotEnoughStockBalanceException(UUID balloonId, int quantity) {
        super("Stock balance of balloon by id: " + balloonId + " less than ordered quantity: " + quantity);
    }
}
