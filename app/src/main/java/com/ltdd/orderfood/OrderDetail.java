package com.ltdd.orderfood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltdd.orderfood.Common.Common;
import com.ltdd.orderfood.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {
    TextView order_id, order_phone, order_address, order_total;
    String order_id_value ="";
    RecyclerView lv_orders;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order_id = (TextView) findViewById(R.id.order_id);
        order_phone = (TextView) findViewById(R.id.order_phone);
        order_total = (TextView) findViewById(R.id.order_total);
        order_address = (TextView) findViewById(R.id.order_address);

        lv_orders = findViewById(R.id.lv_orders);
        lv_orders.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lv_orders.setLayoutManager(layoutManager);

        if(getIntent() != null)
            order_id_value = getIntent().getStringExtra("OrderId");

        order_id.setText(String.format("ID ĐƠN HÀNG: %s",order_id_value));
        order_phone.setText(String.format("SĐT: %s",Common.currentRequest.getPhone()));
        order_address.setText(String.format("ĐỊA CHỈ: %s", Common.currentRequest.getAddress()));
        order_total.setText(String.format("GIÁ TIỀN: %s",Common.currentRequest.getTotal()));

        OrderDetailAdapter adapter = new OrderDetailAdapter(Common.currentRequest.getFoods());
        adapter.notifyDataSetChanged();
        lv_orders.setAdapter(adapter);

    }
}