package com.example.soul.pr9_shop.util;

import android.widget.EditText;

import com.example.soul.pr9_shop.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/18/18.
 */

public class ProductUtils {

        public static List<Product> getProductList(){
            List<Product> products = new ArrayList<Product>();
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());
            products.add(new Product());

            return products;
        }
}
