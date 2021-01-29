package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Meals;
import com.example.foodapp.view.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context c;

    public RecyclerViewMealByCategory(List<Meals.Meal> meals, Context c) {
        this.meals = meals;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_recycler_meal,
                parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.mealNameCategory.setText(meals.get(position).getStrMeal());
        Picasso.get().load(meals.get(position).getStrMealThumb()).into(holder.mealThumbCategory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c, DetailsActivity.class);
                intent.putExtra("details",meals.get(position).getStrMeal());
                Log.d("mealNameDetail",meals.get(position).getStrMeal());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView mealThumbCategory;

        TextView mealNameCategory;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameCategory=itemView.findViewById(R.id.mealNameCategory);
            mealThumbCategory=itemView.findViewById(R.id.mealThumbCategory);
        }

    }
}
