/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.foodapp.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Categories;

import com.example.foodapp.view.category.CategoryActivity;
import com.example.foodapp.view.home.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;



public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private List<Categories.Category> categories;
    private Context context;


    public RecyclerViewHomeAdapter(List<Categories.Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_category,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder viewHolder, int i) {

        String strCategoryThum = categories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);

        String strCategoryName = categories.get(i).getStrCategory();
        viewHolder.categoryName.setText(strCategoryName);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CategoryActivity.class);
                intent.putExtra("extraGategory",(Serializable) categories);
                intent.putExtra("extraPosition", i);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryThumb;

        TextView categoryName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryThumb=itemView.findViewById(R.id.categoryThumb);
            categoryName=itemView.findViewById(R.id.categoryName);
        }

    }
}
