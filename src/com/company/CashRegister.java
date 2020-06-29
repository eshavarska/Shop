package com.company;

import java.util.Calendar;
import java.util.HashMap;

public class CashRegister {
    private Cashier cashier;
    private double profit;
    private Shop shop;
    private Receipt receipt;
    private Cart cart;

    public CashRegister(Cashier cashier, Shop shop) {
        this.shop = shop;
        this.cashier = cashier;
        profit = 0;
    }

    public void newClient(Calendar date, Cart cart){
        this.cart = cart;
        receipt = new Receipt(cashier,date, shop);
        shop.increaseReceiptCount();
    }

    public void addProducts(){
        HashMap<Product, Double> productList = cart.getProductList();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", Working cashier: " + cashier.getName());
                System.out.println();
                productList.entrySet().forEach(entry->{
                    try {
                        addProduct(entry.getKey(), entry.getValue());
                        System.out.println("Adding product on register" + Thread.currentThread().getName() + ": " + entry.getKey().getName() + ", qty: " + entry.getValue());
                    } catch (InsufficientQuantityException | InvalidQuantityException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void addProduct(Product product, double quantity) throws InsufficientQuantityException, InvalidQuantityException{
        if(quantity>0){
            if(quantity > shop.getProductQuantity(product))
                throw new InsufficientQuantityException(product, quantity - shop.getProductQuantity(product));
            else{
                receipt.addProduct(product, quantity);
                shop.sellProduct(product,quantity);
            }
        }else
            throw new InvalidQuantityException(product);
    }

    public void payment(){
        receipt.print();
        receipt.saveToFile();
        profit += receipt.getProfit();
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "CashRegister{" +
                "cashier=" + cashier +
                ", profit=" + profit +
                ", shop=" + shop +
                ", receipt=" + receipt +
                ", cart=" + cart +
                '}';
    }
}
