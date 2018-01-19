package com.example.soul.pr9_shop.data;

import com.example.soul.pr9_shop.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/18/18.
 */

public class RepositoryImpl implements Repository {

    private static RepositoryImpl instance;
    private final Database database;

    private RepositoryImpl(Database database) {
        this.database = database;
    }

    public static RepositoryImpl getInstance(Database database) {
        if (instance == null) {
            instance = new RepositoryImpl(database);
        }
        return instance;
    }

    @Override
    public List<Product> getProducts() {
        return database.getProducts();
    }

    @Override
    public void addProduct(Product product) {
        database.addProduct(product);
    }

    @Override
    public void deleteProduct(int position) {
        database.deleteProduct(position);
    }

    @Override
    public void clearAllProducts() {
        database.clearAllProducts();
    }

    @Override
    public void setProductList(ArrayList<Product> list) {
        database.replaceList(list);
    }

    @Override
    public Product getProduct(int position) {
        return database.getProduct(position);
    }
}
