package com.example.soul.pr9_shop.ui.main;

import android.arch.lifecycle.ViewModel;

import com.example.soul.pr9_shop.data.Repository;
import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.data.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by soul on 1/19/18.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    private ArrayList<Product> data;
    private ArrayList<OrderProduct> orderData;
    private final Repository repository;

    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    public ArrayList<Product> getData() {
        if (data == null) {
            data = (ArrayList<Product>) repository.getProducts();
        }
        return data;
    }

    public ArrayList<OrderProduct> getOrderData() {
        if (orderData == null) {
            orderData = (ArrayList<OrderProduct>) repository.getOrderList();
        }
        return orderData;
    }

}