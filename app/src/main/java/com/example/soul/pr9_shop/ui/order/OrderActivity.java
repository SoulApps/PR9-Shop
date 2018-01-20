package com.example.soul.pr9_shop.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.soul.pr9_shop.R;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    //we call to this activity
    public static void start(Activity activity) {
        Intent intent;
        intent = new Intent(activity, OrderActivity.class);
        activity.startActivity(intent);
    }
}
