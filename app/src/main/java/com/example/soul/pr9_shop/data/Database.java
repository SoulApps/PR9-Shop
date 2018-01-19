package com.example.soul.pr9_shop.data;

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

    private Database() {
        // Initial data.
        productList = ProductUtils.getProductList();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
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

    public Product getProduct(int position) {
        return productList.get(position);
    }
}
