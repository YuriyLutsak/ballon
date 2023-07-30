package com.balloon_spring_jpa.balloon.exception;

public class StockBalanceException extends RuntimeException{

    public StockBalanceException(int quantity) {
        super("stock balance of this kind of balloons less then in your order on: " + quantity);
    }
}
