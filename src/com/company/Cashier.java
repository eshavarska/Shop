package com.company;

public class Cashier {
    private final int ID_number;
    private String name;

    public Cashier(int ID_number, String name) {
        this.ID_number = ID_number;
        this.name = name;
    }

    public int getID_number() {
        return ID_number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "ID_number=" + ID_number +
                ", name='" + name + '\'' +
                '}';
    }
}
