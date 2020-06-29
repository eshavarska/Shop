package com.company;

public class InsufficientQuantityException extends Exception {
    private final Product product;
    private final double difference;

    public InsufficientQuantityException(Product product, double difference) {
        this.product = product;
        this.difference = difference;
    }

    public Product getProduct() {
        return product;
    }

    public double getDifference() {
        return difference;
    }

    @Override
    public String toString() {
        return "InsufficientQuantityException{" +
                "product=" + product +
                ", insufficient quantity=" + difference +
                '}';
    }
}
