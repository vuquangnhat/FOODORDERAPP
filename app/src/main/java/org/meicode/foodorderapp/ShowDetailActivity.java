package org.meicode.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.meicode.foodorderapp.helper.Managerment_Cart;
import org.meicode.foodorderapp.item.FoodDomain;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView add_to_cart_btn;
    private TextView title_Txt,fee_Txt,description_Txt,numberOrder_Txt;
    private ImageView plusBtn,minusBtn,picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private Managerment_Cart managerment_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managerment_cart = new Managerment_Cart(this);
        initview();
        getBundle();
    }

    private void getBundle() {
        object =(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);



        title_Txt.setText(object.getTitle());
        fee_Txt.setText("$"+object.getFee());
        description_Txt.setText(object.getDescription());
        numberOrder_Txt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder +1;
                numberOrder_Txt.setText(String.valueOf(numberOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder < 1) {
                    Toast.makeText(ShowDetailActivity.this, "NumberOrder is small than zero", Toast.LENGTH_SHORT).show();
                }else {
                    numberOrder = numberOrder -1;
                }
                numberOrder_Txt.setText(String.valueOf(numberOrder));
            }
        });
        add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberIncart(numberOrder);
                managerment_cart.insertFood(object);
            }
        });

    }

    private void initview() {
        add_to_cart_btn = findViewById(R.id.addTocartBtn);
        title_Txt = findViewById(R.id.titleTxt);
        fee_Txt = findViewById(R.id.priceTxt);
        description_Txt = findViewById(R.id.descriptionTxt);
        numberOrder_Txt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);
    }
}