package com.example.soul.pr9_shop.data.local;

import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.data.model.Product;
import com.example.soul.pr9_shop.util.ProductUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/18/18.
 */

public class Database {
    private static Database instance;
    private final List<Product> productList;
    private final List<OrderProduct> orderList;

    private Database() {
        // Initial data.
        productList = ProductUtils.getProductList();
        orderList = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public Product getProduct(int position) {
        return productList.get(position);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void deleteProduct(int position) {
        productList.remove(position);
    }


    public void clearAllProducts() {
        productList.clear();
    }

    public void replaceList(ArrayList<Product> newList) {
        productList.clear();
        for (int i = 0; i < newList.size(); i++) {
            productList.add(newList.get(i));
        }
    }

    public OrderProduct getOrderProduct(int position) {
        return orderList.get(position);
    }

    public List<OrderProduct> getOrderList() {
        return orderList;
    }

    public void addToOrder(OrderProduct orderProduct) {
        orderList.add(orderProduct);
    }

    public void deleteFromOrder(int position) {
        orderList.remove(position);
    }


    public void clearOrderList() {
        orderList.clear();
    }

    public void replaceOrderList(ArrayList<OrderProduct> newList) {
        orderList.clear();
        for (int i = 0; i < newList.size(); i++) {
            orderList.add(newList.get(i));
        }
    }

    public void updateQuantity (int index, int quantity){
        orderList.get(index).setQuantity(quantity);
    }



}
