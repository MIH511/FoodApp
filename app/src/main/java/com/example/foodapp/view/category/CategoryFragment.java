package com.example.foodapp.view.category;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.RecyclerViewMealByCategory;
import com.example.foodapp.models.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryFragment extends Fragment implements CategoryView{

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageCategory;
    ImageView imageCategoryBg;
    TextView textCategory;
    AlertDialog.Builder descDialog;
    CardView cardCategory;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        imageCategory=view.findViewById(R.id.imageCategory);
        imageCategoryBg=view.findViewById(R.id.imagCategoryBg);
        textCategory=view.findViewById(R.id.mealCategory);
        cardCategory=view.findViewById(R.id.cardCategory);

        cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments()!=null){
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(imageCategory);
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(imageCategoryBg);
            descDialog=new AlertDialog.Builder(getActivity()).setTitle(getArguments().getString("EXTRA_DATA_NAME")).setMessage("EXTRA_DATA_DESC");
            CategoryPresenter presenter=new CategoryPresenter(this);
            presenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        RecyclerViewMealByCategory adapter=new RecyclerViewMealByCategory(meal,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {

    }
}
