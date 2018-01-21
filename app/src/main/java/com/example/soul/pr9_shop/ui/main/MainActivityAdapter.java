package com.example.soul.pr9_shop.ui.main;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soul.pr9_shop.R;
import com.example.soul.pr9_shop.data.local.Repository;
import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.data.model.Product;
import com.example.soul.pr9_shop.util.OrderUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Soul on 19/01/2018.
 */

public class MainActivityAdapter extends PagerAdapter {

    @BindView(R.id.activity_main_page_imgProduct)
    ImageView mainPageImgProduct;
    @BindView(R.id.activity_main_page_lblProductTitle)
    TextView mainPageProductTitle;
    @BindView(R.id.activity_main_page_lblProductManufacturer)
    TextView mainPageProductManufacturer;
    @BindView(R.id.activity_main_page_ratingBar)
    RatingBar mainPageRatingBar;
    @BindView(R.id.activity_main_page_lblPrice)
    TextView mainPagePrice;
    @BindView(R.id.activity_main_page_CircleIndicator)
    CircleIndicator mainPageCircleIndicator;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private final LayoutInflater mLayoutInflater;
    private ViewPager viewPager;
    private static final int NUMBER_OF_PAGES = 5, DEFAULT_QUANTITY = 1;
    private Repository repository;
    List<OrderProduct> orderList;
    private Context context;

    public MainActivityAdapter(Context context, ViewPager viewPager, Repository mRepository) {
        mLayoutInflater = LayoutInflater.from(context);
        this.viewPager = viewPager;
        this.repository = mRepository;
        this.context = context;
        orderList = repository.getOrderList();
    }

    @Override
    public int getCount() {
        // todo retornar el numero de productos de la BBDD
        return NUMBER_OF_PAGES;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = mLayoutInflater.inflate(R.layout.activity_main_page, collection, false);
        setupPage(view, position);
        collection.addView(view, 0);
        return view;
    }

    private void setupPage(View view, int position) {
        ButterKnife.bind(this,view);
        mainPageCircleIndicator.setViewPager(viewPager);
        // Obtain the current item (same that the item on the viewPager -1)
        final Product product = repository.getProduct(position);
        // Inflate the layout
        // Image with Picasso
        Picasso.with(view.getContext()).load(product.getImage()).into(mainPageImgProduct);
        mainPageProductTitle.setText(product.getProductName());
        mainPagePrice.setText(String.format("%s %s", String.valueOf(product.getPrice()), "€"));
        mainPageRatingBar.setRating(product.getRating());
        mainPageProductManufacturer.setText(product.getProductManufacturer());
    }

    // Obtain the product and send it to the OrderList
    @OnClick(R.id.fab)
    void onFabClick() {
        Product product = repository.getProduct(viewPager.getCurrentItem());
        addToCart(product);
        Toast.makeText(context, String.format("Producto Agregado: %s", product.getProductName()), Toast.LENGTH_SHORT).show();
    }

    private void addToCart(Product product) {
        OrderProduct orderProduct;
        int index = -1;
        // if orderList is empty
        if (orderList.isEmpty()) {
            // we add the product to the cartList
            orderProduct = new OrderProduct(DEFAULT_QUANTITY, product.getProductName(), product);
            repository.addToOrder(orderProduct);
        } else {
            // we must check if they the product is added or not
            index = OrderUtils.increaseProductIfExists(product, orderList);
            if (index != -1) {
                repository.updateQuantity(index, orderList.get(index).getQuantity());
            } else {
                // we add the product to the cartList
                orderProduct = new OrderProduct(DEFAULT_QUANTITY, product.getProductName(), product);
                repository.addToOrder(orderProduct);
            }
        }

    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void startUpdate(ViewGroup arg0) {
    }

    @Override
    public void finishUpdate(ViewGroup arg0) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable parcelable, ClassLoader arg1) {
    }

}
