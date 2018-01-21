package com.example.soul.pr9_shop.util;

import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.data.model.Product;

import java.util.List;

/**
 * Created by soul on 1/20/18.
 */

public class OrderUtils {

    // This method will check for each product in order list if exists
    public static int increaseProductIfExists(Product product, List<OrderProduct> orderList) {
        boolean exists = false;
        int index = 0;
        int length = orderList.size();
        OrderProduct orderProduct;
        while (index < length && !exists) {
            orderProduct = orderList.get(index);
            if (orderProduct.getIdProduct().equals(product.getProductName())) {
                // if product exists, set exists to true
                orderProduct.setQuantity(orderProduct.getQuantity() +1);
                exists = true;
            }else
                index++;
        }
        if(!exists){
            index = -1;
        }
        return index;
    }

    // For each product, calculate the total price
    public static float calculateTotal(List<OrderProduct> orderList){
        float total = 0.0f;
        float productPrice;
        for (OrderProduct product: orderList) {
            productPrice = product.getQuantity() * product.getProduct().getPrice();
            total += productPrice;
        }
        return total;
    }
}
