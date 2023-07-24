package org.meicode.foodorderapp.helper;

import android.content.Context;
import android.widget.Toast;

import org.meicode.foodorderapp.Chang_Number_Item_Listener;
import org.meicode.foodorderapp.item.FoodDomain;

import java.util.ArrayList;

public class Managerment_Cart {
    private Context context;
    private TinyDB tinyDB;

    public Managerment_Cart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready) {
            listFood.get(n).setNumberIncart(item.getNumberIncart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Add To Your Cart", Toast.LENGTH_SHORT).show();
    }
    public  ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
    public void plusNumberFood (ArrayList<FoodDomain> listFood, int position, Chang_Number_Item_Listener chang_number_item_listener) {
        listFood.get(position).setNumberIncart(listFood.get(position).getNumberIncart() + 1);
        tinyDB.putListObject("CartList",listFood);
        chang_number_item_listener.changed();
    }
    public void minusNumberFôod(ArrayList<FoodDomain> listfood, int position, Chang_Number_Item_Listener chang_number_item_listener) {
        if(listfood.get(position).getNumberIncart() == 1) {
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberIncart(listfood.get(position).getNumberIncart() - 1 );
        }
        tinyDB.putListObject("CartList",listfood);
        chang_number_item_listener.changed();
    }

    public Double getTotalFee() {
        ArrayList<FoodDomain> listfood = getListCart();
        double fee = 0;
        for(int i = 0 ; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberIncart());
        }
        return fee;
    }
}
