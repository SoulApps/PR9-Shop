package com.example.soul.pr9_shop.ui.order;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soul.pr9_shop.R;
import com.example.soul.pr9_shop.data.local.Repository;
import com.example.soul.pr9_shop.data.model.OrderProduct;
import com.example.soul.pr9_shop.util.OrderUtils;

import java.util.List;

/**
 * Created by soul on 1/20/18.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private static Repository mRepository;
    private View mEmptyView;
    private static OnIncreasedItemListener onIncreasedItemListener;
    private  static OnDecreasedItemListener onDecreasedItemListener;

    private final RecyclerView.AdapterDataObserver mObserver =
            new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    checkEmptyViewVisibility();
                }

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    checkEmptyViewVisibility();
                }

                @Override
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    super.onItemRangeRemoved(positionStart, itemCount);
                    checkEmptyViewVisibility();
                }
            };


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_order_product, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView, this);
        return viewHolder;
    }

    public void setOnIncreasedItemClick(OnIncreasedItemListener listener) {
        this.onIncreasedItemListener = listener;
    }

    public void setOnDecresedItemClick(OnDecreasedItemListener listener) {
        this.onDecreasedItemListener = listener;
    }


    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(mRepository.getOrderProduct(position), position);
    }

    @Override
    public int getItemCount() {
        return mRepository == null ? 0 : mRepository.orderListSize();
    }

    public void setData(Repository data) {
        mRepository = data;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mRepository.deleteProductFromCart(position);
        notifyItemRemoved(position);
    }

    public void setEmptyView(@NonNull View emptyView) {
        if (mEmptyView != null) {
            unregisterAdapterDataObserver(mObserver);
        }
        mEmptyView = emptyView;
        registerAdapterDataObserver(mObserver);
    }


    private void checkEmptyViewVisibility() {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void onDestroy() {
        if (mEmptyView != null) {
            unregisterAdapterDataObserver(mObserver);
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        // vars
        private final TextView orderProductLblQuantity;
        private final TextView orderProductLblProduct;
        private final TextView orderProductLblPrice;
        private final ImageButton orderProductBtnAddProduct;
        private final ImageButton orderProductBtnDeleteProduct;
        private final OrderAdapter orderAdapter;



        // El constructor recibe la vista correspondiente al elemento.
        public ViewHolder(View itemView, OrderAdapter orderAdapter) {
            super(itemView);
            orderProductLblQuantity = itemView.findViewById(R.id.activity_order_product_lblProductQuantity);
            orderProductLblProduct = itemView.findViewById(R.id.activity_order_product_lblArticle);
            orderProductBtnAddProduct = itemView.findViewById(R.id.activity_order_product_btnAddProduct);
            orderProductBtnDeleteProduct = itemView.findViewById(R.id.activity_order_product_btnDeleteProduct);
            orderProductLblPrice = itemView.findViewById(R.id.activity_order_product_lblPrice);
            this.orderAdapter = orderAdapter;
        }

        @SuppressLint("DefaultLocale")
        public void bind(OrderProduct orderProduct, final int position) {
            orderProductLblQuantity.setText(String.valueOf(orderProduct.getQuantity()));
            orderProductLblProduct.setText(orderProduct.getIdProduct());
            orderProductLblPrice.setText(String.format("%.2f",setOrderProductPrice(orderProduct.getQuantity(),
                    orderProduct.getProduct().getPrice())));
            orderProductBtnAddProduct.setOnClickListener((view) -> {
                increaseData(view, orderProduct, position);
            });
            orderProductBtnDeleteProduct.setOnClickListener((view) -> {
                decreaseData(view,orderProduct, position);
            });
        }

        private void increaseData(View view, OrderProduct orderProduct, int position) {
            // increase the product
            onIncreasedItemListener.onIncreasedItem(view, orderProduct, position);
            orderProduct.setQuantity(orderProduct.getQuantity() + 1);
            orderAdapter.notifyItemChanged(position);
        }


        private void decreaseData(View view, OrderProduct orderProduct, int position) {
            // decrease the product
            int quantity = orderProduct.getQuantity();
            onDecreasedItemListener.onDecreasedItem(view, orderProduct, position);
            if (quantity > 1) {
                orderProduct.setQuantity(quantity - 1);
                orderAdapter.notifyItemChanged(position);
            } else {
                // if is 1 and we decrease the product, we delete it
                orderAdapter.removeItem(position);
                orderAdapter.notifyDataSetChanged();
            }
        }

        private float setOrderProductPrice(int quantity, float price) {
            float finalPrice;
            finalPrice = quantity * price;
            return finalPrice;
        }
    }

    @SuppressWarnings("UnusedParameters")
    public interface OnIncreasedItemListener {
        void onIncreasedItem(View view, OrderProduct orderProduct, int position);
    }

    @SuppressWarnings("UnusedParameters")
    public interface OnDecreasedItemListener {
        void onDecreasedItem(View view, OrderProduct orderProduct, int position);
    }

}
