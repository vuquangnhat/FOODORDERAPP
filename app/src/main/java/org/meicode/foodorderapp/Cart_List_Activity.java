package org.meicode.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.meicode.foodorderapp.adapter.Cart_List_Adapter;
import org.meicode.foodorderapp.helper.Managerment_Cart;

public class Cart_List_Activity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private  RecyclerView recyclerViewList;
    private Managerment_Cart managermentCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managermentCart = new Managerment_Cart(this);

        initView();
        initList();
        caculate_Cart();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.CartBtn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart_List_Activity.this,Cart_List_Activity.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart_List_Activity.this,MainActivity.class));

            }
        });
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.CartView);
        totalFeeTxt = findViewById(R.id.total_item_feeTxt);
        taxTxt = findViewById(R.id.Tax_feeTxt);
        deliveryTxt = findViewById(R.id.Delivery_Service_feeTxt);
        totalTxt = findViewById(R.id.Total_feeTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.CartView);
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new Cart_List_Adapter(managermentCart.getListCart(), this, new Chang_Number_Item_Listener() {
            @Override
            public void changed() {
                caculate_Cart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managermentCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void caculate_Cart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managermentCart.getTotalFee() * percentTax )* 100)/100;
        double total = Math.round((managermentCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managermentCart.getTotalFee() * 100)/100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }
}