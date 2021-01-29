package com.example.foodapp.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodapp.R;
import com.example.foodapp.Utils;
import com.example.foodapp.adapter.RecyclerViewHomeAdapter;
import com.example.foodapp.adapter.ViewPagerHeaderAdapter;
import com.example.foodapp.models.Categories;
import com.example.foodapp.models.Meals;
import com.example.foodapp.view.category.CategoryActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeView{

    RecyclerView recyclerViewCategory;
    ViewPager viewPagerMeal;
    HomePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory=findViewById(R.id.recyclerCategory);
        viewPagerMeal=findViewById(R.id.ViewPagerHeader);
        presenter=new HomePresenter(this);
        presenter.getCategories();
        presenter.getMeals();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter viewPagerHeaderAdapter=new ViewPagerHeaderAdapter(meal,this);
        viewPagerMeal.setAdapter(viewPagerHeaderAdapter);
        viewPagerMeal.setPadding(20,0,150,0);
        viewPagerHeaderAdapter.notifyDataSetChanged();
        for (Meals.Meal meal1:meal){
        Log.d("meals lists", String.valueOf(meal1));
        }
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter recyclerViewHomeAdapter=new RecyclerViewHomeAdapter(category,this);
        recyclerViewCategory.setAdapter(recyclerViewHomeAdapter);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setClipToPadding(false);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        recyclerViewHomeAdapter.notifyDataSetChanged();


    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Title",message);
    }
}
