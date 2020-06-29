package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Product {
    private final int ID;
    private String name;
    private double price;
    private final Calendar expirationDate;

    public Product(int ID, String name, double price, Calendar expirationDate) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expirationDate=" + format.format(expirationDate.getTime()) +
                '}';
    }
}
