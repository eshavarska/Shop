package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Receipt{
    private static int numberOfReceipts = 0;
    private final int currentNumber;
    private Cashier cashier;
    private Calendar dateAndTime;
    private ArrayList<ReceiptEntry> goods;
    private Shop shop;
    private double total;

    public Receipt(Cashier cashier, Calendar dateAndTime, Shop shop) {
        currentNumber = ++numberOfReceipts;
        this.cashier = cashier;
        this.dateAndTime = dateAndTime;
        this.shop = shop;
        goods = new ArrayList<ReceiptEntry>();
        total = 0;
    }

    public void addProduct(Product product, double quantity){
        boolean found = false;
        try{
            for(ReceiptEntry r : goods){
                if(r.getProduct() == product){
                    found = true;
                    r.add(quantity);
                    total+=r.getPrice();
                }
            }
            if(!found) {
                ReceiptEntry temp = new ReceiptEntry(product, quantity);
                goods.add(temp);
                total+=temp.getPrice();
            }
        } catch (InvalidQuantityException e){
            e.printStackTrace();
        }
    }

    public void print(){
        SimpleDateFormat format = new SimpleDateFormat("d.MM.yyyy, HH:mm");
        System.out.println("============================================================");
        System.out.printf("%30s", shop.getName() + "\n");
        System.out.println("============================================================");
        System.out.println();
        System.out.printf("%-30s%30s%n","Receipt number: " + currentNumber, "Date: " + format.format(dateAndTime.getTime()));
        System.out.println("Cashier: " + cashier.getName());
        System.out.println();
        System.out.println("Products:");
        for (ReceiptEntry r : goods){
            System.out.printf("%-30s%30.2f%n", r.getProduct().getName(), r.getProduct().getPrice());
            System.out.printf("%-30s%30.2f%n", "  x"+r.getQuantity(),r.getPrice());
        }
        System.out.println();
        System.out.printf("%50s%10.2f%n", "Total: ", total);
        System.out.println();
        System.out.println("============================================================");
        System.out.println("Thank you for shopping with us!");
        System.out.println("============================================================");
    }

    public void saveToFile(){
        String outputFilename = "Receipt_" + currentNumber + ".txt";
        try (FileWriter fout = new FileWriter(new File(outputFilename), true);) {
            PrintWriter printWriter = new PrintWriter( fout );
            SimpleDateFormat format = new SimpleDateFormat("d.MM.yyyy, HH:mm");
            printWriter.println("============================================================");
            printWriter.printf("%30s", shop.getName() + "\n");
            printWriter.println("============================================================");
            printWriter.println();
            printWriter.printf("%-30s%30s%n","Receipt number: " + currentNumber, "Date: " + format.format(dateAndTime.getTime()));
            printWriter.println("Cashier: " + cashier.getName());
            printWriter.println();
            printWriter.println("Products:");
            for (ReceiptEntry r : goods){
                printWriter.printf("%-30s%30.2f%n", r.getProduct().getName(), r.getProduct().getPrice());
                printWriter.printf("%-30s%30.2f%n", "  x"+r.getQuantity(),r.getPrice());
            }
            printWriter.println();
            printWriter.printf("%50s%10.2f%n", "Total: ", total);
            printWriter.println();
            printWriter.println("============================================================");
            printWriter.println("Thank you for shopping with us!");
            printWriter.println("============================================================");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getProfit(){
        String inputFilename = "Receipt_" + currentNumber + ".txt";
        double profit = 0;
        try (FileReader fis = new FileReader(new File(inputFilename))) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("Total:")) {
                    StringBuilder t = new StringBuilder(line);
                    int i = line.indexOf("Total:") + 6;
                    while(line.charAt(i)==' ') i++;
                    profit = Double.parseDouble(line.substring(i));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profit;
    }

    public double getProductQuantity(Product product){
        for(ReceiptEntry re : goods)
            if(re.getProduct() == product) return re.getQuantity();
        return 0;
    }

    public static int getNumberOfReceipts() {
        return numberOfReceipts;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public Calendar getDateAndTime() {
        return dateAndTime;
    }

    public ArrayList<ReceiptEntry> getGoods() {
        return goods;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "currentNumber=" + currentNumber +
                ", cashier=" + cashier +
                ", dateAndTime=" + dateAndTime +
                ", goods=" + goods +
                ", total=" + total +
                '}';
    }
}
