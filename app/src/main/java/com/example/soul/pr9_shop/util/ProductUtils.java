package com.example.soul.pr9_shop.util;

import com.example.soul.pr9_shop.data.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soul on 1/18/18.
 */

public class ProductUtils {

        public static List<Product> getProductList(){
            List<Product> products = new ArrayList<>();
            products.add(new Product("GIFT PLUS MINUS 1","ECC Ediciones", 4.2f, 9.45f,
                    "https://www.bedetheque.com/media/Couvertures/Couv_297452.jpg"));
            products.add(new Product("YOUR LIE IN APRIL 01","Milky Way Ediciones", 5.0f, 7.60f,
                    "https://s3.amazonaws.com/media-us-standrad/wp-content/uploads/2015/12/13013303/0-8-800x1200.jpg"));
            products.add(new Product("KAGEROU DAZE 09","Ivréa", 3.5f, 7.50f,
                    "http://www.milcomics.com/spa/item/extractimg.cgi?action=large&code=ART21432"));
            products.add(new Product("TEN COUNT 02","Ivréa", 2.4f, 8.08f,
                    "http://www.raccoongames.es/img/productos/2018/01/10/IVRTENCOU02.JPG"));
            products.add(new Product("MOB PSYCHO 100 Nº09","Ivréa", 1.5f, 7.33f,
                    "https://www.akiracomics.com/media/products/51119/51119-0-med.jpg"));

            return products;
        }
}
