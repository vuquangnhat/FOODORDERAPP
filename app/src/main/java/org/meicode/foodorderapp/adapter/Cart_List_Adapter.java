package org.meicode.foodorderapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.meicode.foodorderapp.Chang_Number_Item_Listener;
import org.meicode.foodorderapp.R;
import org.meicode.foodorderapp.helper.Managerment_Cart;
import org.meicode.foodorderapp.item.FoodDomain;

import java.util.ArrayList;

public class Cart_List_Adapter extends RecyclerView.Adapter<Cart_List_Adapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private Managerment_Cart managerment_cart;
    private Chang_Number_Item_Listener chang_number_item_listener;

    public Cart_List_Adapter(ArrayList<FoodDomain> foodDomains, Context context, Chang_Number_Item_Listener chang_number_item_listener) {
        this.foodDomains = foodDomains;
        this.managerment_cart = new Managerment_Cart(context);
        this.chang_number_item_listener = chang_number_item_listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachitem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberIncart()*foodDomains.get(position).getFee())*100)/100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberIncart()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerment_cart.plusNumberFood(foodDomains, position, new Chang_Number_Item_Listener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        chang_number_item_listener.changed();
                    }
                });
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerment_cart.minusNumberFôod(foodDomains, position, new Chang_Number_Item_Listener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        chang_number_item_listener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachitem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_Txt);
            feeEachitem = itemView.findViewById(R.id.feeEachitem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachitem);
            num = itemView.findViewById(R.id.number_itemTxt);
            plusItem = itemView.findViewById(R.id.plus_cartBtn);
            minusItem = itemView.findViewById(R.id.minus_cartBtn);
        }
    }
}
