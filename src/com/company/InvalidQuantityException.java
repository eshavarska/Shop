package com.company;

public class InvalidQuantityException extends Exception {
    private final Product product;

    public InvalidQuantityException(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "InvalidQuantityException{" +
                "product=" + product +
                "; Value cannot be equals or less than 0." +
                '}';
    }
}
