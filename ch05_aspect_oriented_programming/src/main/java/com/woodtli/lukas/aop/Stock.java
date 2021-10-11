package com.woodtli.lukas.aop;

public class Stock {
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public Stock(int quantity) {
        super();
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "quantity=" + quantity +
                '}';
    }
}
