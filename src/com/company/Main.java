package com.company;


import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
	    Cashier jerry = new Cashier(12345, "Jerry Jenkins");
	    Cashier mary = new Cashier(46846, "Mary Malone");

        Calendar date1 = Calendar.getInstance();
        date1.set(2020, 5, 5);
        Calendar date2 = Calendar.getInstance();
        date2.set(2020,9,30);

	    Product milk = new Product(325343, "Milk", 2.3, date1);
	    Product bananas = new Product(46465, "Bananas", 4, date1);
	    Product biscuits = new Product(654684, "Oreo cookies", 3, date2);
	    Product chips = new Product(389756, "Chio Chips", 3.2, date2);
	    Product apples = new Product(598753, "Granny Smith Apples", 1.8, date1);

        Shop lidl = new Shop("Lidl");
        CashRegister reg1 = new CashRegister(jerry, lidl);
        CashRegister reg2 = new CashRegister(mary, lidl);
        lidl.addCashier(jerry);
        lidl.addCashier(mary);
        lidl.addCashRegister(reg1);
        lidl.addCashRegister(reg2);

        try{
            lidl.productDelivery(bananas, 9);
            lidl.productDelivery(biscuits, 4);
            lidl.productDelivery(chips, 5);
            lidl.productDelivery(apples, 3.5);
            lidl.productDelivery(milk, 4);

            System.out.println();
            lidl.checkAllProducts();
            System.out.println();

            Cart cart1 = new Cart(lidl);
            cart1.addProduct(milk, 2);
            cart1.addProduct(milk, 1);
            cart1.addProduct(bananas, 5);
            cart1.addProduct(biscuits, 2);
            Cart cart2 = new Cart(lidl);
            cart2.addProduct(milk, 1);
            cart2.addProduct(bananas, 3);
            cart2.addProduct(apples, 2.5);
            cart2.addProduct(chips, 3);
            reg1.newClient(Calendar.getInstance(), cart1);
            reg2.newClient(Calendar.getInstance(), cart2);
            reg2.addProducts();
            reg1.addProducts();

            //Sleep main thread while others are working
            Thread t = Thread.currentThread();
            try {
                Thread.sleep(1000);}
            catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
             }


        reg1.payment();
            reg2.payment();

        } catch (InsufficientQuantityException | InvalidQuantityException e){
            e.printStackTrace();
        }


        System.out.println();
        lidl.checkAllProducts();
        System.out.println();

        System.out.println("Lidl profit: " + lidl.profit() + "; Receipt count: " + lidl.getReceiptCount());

        System.out.println();

        Util.readReceipt("Receipt_1.txt");

    }
}
