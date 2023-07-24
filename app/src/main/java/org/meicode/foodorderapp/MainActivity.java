package org.meicode.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.meicode.foodorderapp.adapter.Catergory_Adapter;
import org.meicode.foodorderapp.adapter.Popular_Adapter;
import org.meicode.foodorderapp.item.CatergoryDomain;
import org.meicode.foodorderapp.item.FoodDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCateogorylist,recyclerViewPopularlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //10:27
        //19:34
        //24:06
        //42:11
        //51:25
        //1:02:43
        //1:15:21
        //1:27:43
        //1:43:28
        //1:45:37
        //1:52:08
        //1:58:17 finish show detail activity
        //2:05:35
        //2:24:00
        //2:34:37
        recyclerViewCateogory();
        recyclerviewPopular();
        bottomNavigation();
    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.CartBtn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Cart_List_Activity.class));

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));

            }
        });
    }

    private void recyclerViewCateogory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCateogorylist = findViewById(R.id.recyclerViewcategory);
        recyclerViewCateogorylist.setLayoutManager(linearLayoutManager);

        ArrayList<CatergoryDomain> catergory = new ArrayList<>();
        catergory.add(new CatergoryDomain("Pizza","cat_1"));
        catergory.add(new CatergoryDomain("Burger","cat_2"));
        catergory.add(new CatergoryDomain("Hotdog","cat_3"));
        catergory.add(new CatergoryDomain("Drink","cat_4"));
        catergory.add(new CatergoryDomain("Donut","cat_5"));

        adapter = new Catergory_Adapter(catergory);
        recyclerViewCateogorylist.setAdapter(adapter);


    }
    private void recyclerviewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularlist = findViewById(R.id.recyclerViewpopular);
        recyclerViewPopularlist.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foddList = new ArrayList<>();
        foddList.add(new FoodDomain("Pepperoni Pizza","pizza","Decription:\nslices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce",9.76,1));
        foddList.add(new FoodDomain("Cheese Burger","pop_2","Decription:\nbeef, Gouda Cheese, Special Sauce, Lettuce, tomato",8.79,2));
        foddList.add(new FoodDomain("Vegetable pizza", "pop_3","Decription:\nolive oil Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil",9.1,3));

        foddList.add(new FoodDomain("Pepperoni Pizza","pizza","Decription:\nslices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce",8.76,4));
        foddList.add(new FoodDomain("Cheese Burger","pop_2","Decription:\nbeef, Gouda Cheese, Special Sauce, Lettuce, tomato",5.79,5));
        foddList.add(new FoodDomain("Vegetable pizza", "pop_3","Decription:\nolive oil Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil",12.1,3));

        adapter2 = new Popular_Adapter(foddList);
        recyclerViewPopularlist.setAdapter(adapter2);

    }
}