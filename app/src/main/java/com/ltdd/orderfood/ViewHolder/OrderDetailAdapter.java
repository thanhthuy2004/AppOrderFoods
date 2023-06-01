package com.chungnguyen.orderfoodserver.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chungnguyen.orderfoodserver.Model.Order;
import com.chungnguyen.orderfoodserver.R;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView name, quantity, price, totalprice, discount;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.pro_name);
        quantity = (TextView) itemView.findViewById(R.id.pro_quantity);
        price = (TextView) itemView.findViewById(R.id.pro_price);
        totalprice = (TextView) itemView.findViewById(R.id.pro_totalprice);
        discount = (TextView) itemView.findViewById(R.id.pro_disc);
    }
}
public class OrderDetailAdapter extends RecyclerView.Adapter<MyViewHolder>{
    List<Order> orders;

    public OrderDetailAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = orders.get(position);
        int totalPrice = Integer.parseInt(order.getPrice()) * Integer.parseInt(order.getQuantity());
        holder.name.setText(String.format("Món: %s", order.getProductName()));
        holder.quantity.setText(String.format("Số lượng: %s", order.getQuantity()));
        holder.price.setText(String.format("Giá: %s", order.getPrice()));
        holder.totalprice.setText(String.format("Tổng: %s", totalPrice));
        holder.discount.setText(String.format("Giảm giá: %s", order.getDiscount()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
