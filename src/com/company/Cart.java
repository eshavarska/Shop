package com.company;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Double> productList;
    private Shop shop;

    public Cart(Shop shop) {
        this.shop = shop;
        productList = new HashMap<>();
    }

    public void addProduct(Product product, double quantity) throws InsufficientQuantityException, InvalidQuantityException {
        if(quantity > 0){
            if(quantity > shop.getProductQuantity(product))
                throw new InsufficientQuantityException(product, quantity - shop.getProductQuantity(product));
            else{
                if (productList.containsKey(product))
                    productList.put(product, productList.get(product)+quantity);
                else
                    productList.put(product, quantity);
            }
        }else
            throw new InvalidQuantityException(product);
    }

    public HashMap<Product, Double> getProductList() {
        return productList;
    }

    public Shop getShop() {
        return shop;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + productList +
                ", shop=" + shop +
                '}';
    }
}
