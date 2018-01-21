package com.example.soul.pr9_shop.ui.order;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soul.pr9_shop.R;
import com.example.soul.pr9_shop.data.local.Database;
import com.example.soul.pr9_shop.data.local.Repository;
import com.example.soul.pr9_shop.data.local.RepositoryImpl;
import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.ui.main.MainActivityViewModel;
import com.example.soul.pr9_shop.ui.main.MainActivityViewModelFactory;
import com.example.soul.pr9_shop.util.OrderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.OnIncreasedItemListener,
        OrderAdapter.OnDecreasedItemListener{


    @BindView(R.id.activity_order_lstOrder)
    RecyclerView activityOrderLstOrder;
    @BindView(R.id.activity_order_lblPrice)
    TextView activityOrderLblPrice;
    @BindView(R.id.activity_order_BtnConfirmOrder)
    Button activityOrderBtnConfirmOrder;
    @BindView(R.id.activity_order_lblEmptyText)
    TextView mEmptyView;

    private OrderAdapter mAdapter;
    private Repository mRepository;
    private float totalPrice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initializeRepositories();
        initViews();
        getTotalPrice();
    }

    private void initializeRepositories() {
        mRepository = RepositoryImpl.getInstance(Database.getInstance());
        MainActivityViewModel mViewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(mRepository)).get(
                MainActivityViewModel.class);
    }

    private void initViews() {
        ButterKnife.bind(this);
        setUpRecyclerView();
        setUpConfirmButton();
    }

    private void setUpConfirmButton() {
        Toast.makeText(this, "Pedido realizado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void setUpRecyclerView() {
        mAdapter = new OrderAdapter();
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.setData(mRepository);
        mAdapter.setOnDecresedItemClick(this);
        mAdapter.setOnIncreasedItemClick(this);
        activityOrderLstOrder.setHasFixedSize(true);
        activityOrderLstOrder.setAdapter(mAdapter);
        activityOrderLstOrder.setLayoutManager(new LinearLayoutManager(
                this,LinearLayoutManager.VERTICAL, false
        ));
        activityOrderLstOrder.setItemAnimator(new DefaultItemAnimator());
    }

    //we call to this activity
    public static void start(Activity activity) {
        Intent intent;
        intent = new Intent(activity, OrderActivity.class);
        activity.startActivity(intent);

    }

    @OnClick(R.id.activity_order_BtnConfirmOrder)
    public void onBtnConfirmOrderClicked() {
        mRepository.clearOrderList();
        Toast.makeText(this, "Producto creado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.onDestroy();
    }

    @Override
    public void onIncreasedItem(View view, OrderProduct orderProduct, int position) {
        increaseTotalPrice(orderProduct);
    }



    @Override
    public void onDecreasedItem(View view, OrderProduct orderProduct, int position) {
        decreaseTotalPrice(orderProduct);
    }

    private void getTotalPrice(){
        totalPrice =  OrderUtils.calculateTotal(mRepository.getOrderList());
        setTotalPrice(totalPrice);
    }

    private void increaseTotalPrice(OrderProduct orderProduct) {
        totalPrice += orderProduct.getProduct().getPrice();
        setTotalPrice(totalPrice);
    }

    private void decreaseTotalPrice(OrderProduct orderProduct) {
        totalPrice -= orderProduct.getProduct().getPrice();
        setTotalPrice(totalPrice);
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        activityOrderLblPrice.setText(String.format("%.2f",totalPrice));
    }
}
