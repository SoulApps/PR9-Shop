package com.example.soul.pr9_shop.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by soul on 1/20/18.
 */

public class OrderProduct implements Parcelable {
    private int quantity;
    private Product product;
    private String idProduct;

    public OrderProduct(int quantity, String idProduct, Product product) {
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeParcelable(this.product, flags);
        dest.writeString(this.idProduct);
    }

    protected OrderProduct(Parcel in) {
        this.quantity = in.readInt();
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.idProduct = in.readString();
    }

    public static final Parcelable.Creator<OrderProduct> CREATOR = new Parcelable.Creator<OrderProduct>() {
        @Override
        public OrderProduct createFromParcel(Parcel source) {
            return new OrderProduct(source);
        }

        @Override
        public OrderProduct[] newArray(int size) {
            return new OrderProduct[size];
        }
    };
}
