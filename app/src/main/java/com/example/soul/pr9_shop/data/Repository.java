package com.example.soul.pr9_shop.data;

import com.example.soul.pr9_shop.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/19/18.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Repository {
    List<Product> getProducts();

    void addProduct(Product product);

    void deleteProduct(int position);

    void clearAllProducts();

    void setProductList (ArrayList<Product> list);

    Product getProduct(int position);
}
