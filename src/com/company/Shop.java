package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private String name;
    private HashMap<Product, Double> productList;
    private ArrayList<Cashier> cashiers;
    private ArrayList<CashRegister> cashRegisters;
    private int receiptCount;

    public Shop(String name) {
        this.name = name;
        productList = new HashMap<>();
        cashiers = new ArrayList<>();
        cashRegisters = new ArrayList<>();
        receiptCount = 0;
    }

    public void productDelivery(Product product, double quantity) throws InvalidQuantityException{
        if(quantity>0){
            if (productList.containsKey(product))
                productList.put(product, productList.get(product)+quantity);
            else
                productList.put(product, quantity);
        }else throw new InvalidQuantityException(product);
    }

    public void addCashRegister(CashRegister cashRegister){
        cashRegisters.add(cashRegister);
    }

        public void addCashier(Cashier cashier){
        cashiers.add(cashier);
    }

    public double profit(){
        double profit = 0;
        for (CashRegister c : cashRegisters){
            profit += c.getProfit();
        }
        return profit;
    }

    public double getProductQuantity(Product product){
        if (productList.containsKey(product))
            return productList.get(product);
        else return 0;
    }

    public void checkAllProducts(){
        productList.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public synchronized void sellProduct(Product product, double quantity) throws InsufficientQuantityException, InvalidQuantityException{
        if(quantity>0){
            if(productList.containsKey(product)){
                if(productList.get(product) < quantity)
                    throw new InsufficientQuantityException(product, quantity-productList.get(product));
                else
                    productList.put(product, productList.get(product)-quantity);
            }else System.out.println("Product is not sold");
        }else
            throw new InvalidQuantityException(product);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cashier> getCashiers() {
        return cashiers;
    }

    public void increaseReceiptCount() {
        receiptCount++;
    }

    public int getReceiptCount() {
        return receiptCount;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", productList=" + productList +
                ", cashiers=" + cashiers +
                ", cashRegisters=" + cashRegisters +
                '}';
    }
}
