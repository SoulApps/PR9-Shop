package com.example.soul.pr9_shop.ui.main;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.soul.pr9_shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private final LayoutInflater mLayoutInflater;
    private ViewPager viewPager;
    private static final int NUMBER_OF_PAGES = 5;


    public MainActivityAdapter(Context context, ViewPager viewPager) {
        mLayoutInflater = LayoutInflater.from(context);
        this.viewPager = viewPager;
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
    }

    private void show(View view, int position) {
        Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
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
