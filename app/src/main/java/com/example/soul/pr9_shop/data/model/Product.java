package com.example.soul.pr9_shop.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by soul on 1/17/18.
 */

public class Product implements Parcelable {

    private String productName;
    private String productManufacturer;
    private float rating;
    private float price;
    private String image;

    public Product(String productName, String productManufacturer, float rating, float price, String image) {
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productName);
        dest.writeString(this.productManufacturer);
        dest.writeFloat(this.rating);
        dest.writeFloat(this.price);
        dest.writeString(this.image);
    }

    protected Product(Parcel in) {
        this.productName = in.readString();
        this.productManufacturer = in.readString();
        this.rating = in.readFloat();
        this.price = in.readFloat();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
