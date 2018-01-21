package com.example.soul.pr9_shop.data.local;

import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.data.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/19/18.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Repository {
    List<Product> getProducts();
    List<OrderProduct> getOrderList();

    void addProduct(Product product);

    void deleteProduct(int position);

    void clearAllProducts();

    void setProductList (ArrayList<Product> list);

    Product getProduct(int position);

    void addToOrder(OrderProduct orderProduct);

    void deleteProductFromCart(int position);

    void clearOrderList();

    void setOrderList(ArrayList<OrderProduct> list);

    OrderProduct getOrderProduct(int position);

    void updateQuantity(int index, int quantity);

    int orderListSize();
}
