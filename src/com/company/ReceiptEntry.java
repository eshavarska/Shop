package com.company;

public class ReceiptEntry   {
    private Product product;
    private double quantity;
    private double price;

    public ReceiptEntry(Product product, double quantity) throws InvalidQuantityException{
        if(quantity <= 0)
            throw new InvalidQuantityException(product);
        else {
            this.product = product;
            this.quantity = quantity;
            price = product.getPrice()*quantity;
        }
    }

    public void add(double quantity) throws InvalidQuantityException{
        if(quantity <= 0)
            throw new InvalidQuantityException(product);
        else {
            this.quantity+=quantity;
            price = product.getPrice()*this.quantity;
        }
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ReceiptEntry{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
