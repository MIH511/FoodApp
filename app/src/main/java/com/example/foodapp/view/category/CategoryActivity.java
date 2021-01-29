package com.example.foodapp.view.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ViewPagerCategoryAdapter;
import com.example.foodapp.adapter.ViewPagerHeaderAdapter;
import com.example.foodapp.models.Categories;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar=findViewById(R.id.toolBar);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent=getIntent();
        List<Categories.Category> categories= (List<Categories.Category>) intent.getSerializableExtra("extraGategory");
        int position= intent.getIntExtra("extraPosition",0);
        ViewPagerCategoryAdapter adapter=new ViewPagerCategoryAdapter(getSupportFragmentManager(),categories);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position,true);
        adapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
////        if (item.getItemId() == android.R.id.home) {
////           // onBackPressed();
////        }
//        return true;
//    }
}
