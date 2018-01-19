package com.example.soul.pr9_shop.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.soul.pr9_shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_vpContainer)
    ViewPager activityMainVpContainer;


    // TODO limpiar esta clase
    // TODO crear clase de BBDD
    // TODO crear cartActivity
    // TODO visualizar todos los datos.
    // TODO mostrar icono que lleve al carrito de la compra
    // TODO btn de compra en la actividad cart
    // TODO crear BBDD
    // TODO mostrar productos


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        // Initialize viewPager
        MainActivityAdapter adapter =  new MainActivityAdapter(this, activityMainVpContainer);
        activityMainVpContainer.setAdapter(adapter);
    }
}
