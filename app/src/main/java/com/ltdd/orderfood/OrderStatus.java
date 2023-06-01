package com.ltdd.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.orderfood.Common.Common;
import com.ltdd.orderfood.Interface.ItemClickListener;
import com.ltdd.orderfood.Model.Request;
import com.ltdd.orderfood.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.listOrder);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadOrders(Common.currentUser.getPhone());
    }
    private void loadOrders(String phone) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true); // đảo ngược thứ tự các mục trong danh sách
        layoutManager.setStackFromEnd(true);
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.txtOrderId.setText("ID đơn hàng:  "+adapter.getRef(position).getKey());
//                viewHolder.txtDate.setText("Ngày đặt:  "+Common.getDate(Long.parseLong(adapter.getRef(position).getKey())));
                viewHolder.txtDate.setText("Ngày đặt:  "+ model.getDate());
                viewHolder.txtOrderStatus.setText("Trạng thái:  "+Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderPhone.setText("SĐT:  "+model.getPhone());
                viewHolder.txtGmail.setText("Địa chỉ:  "+model.getAddress());
                viewHolder.txtTotal.setText("Tổng:  "+model.getTotal());


                viewHolder.setItemClickListener((view, position1, isLongClick) -> {
                    Intent orderDetail = new Intent(OrderStatus.this, OrderDetail.class);
                    Common.currentRequest = model;
                    orderDetail.putExtra("OrderId", adapter.getRef(position1).getKey());
                    startActivity(orderDetail);

                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
