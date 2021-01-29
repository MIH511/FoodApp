package com.example.foodapp.view.details;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodapp.Utils;
import com.example.foodapp.models.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {

    private DetalisView view;

    public DetailsPresenter(DetalisView view) {
        this.view = view;
    }

    void getMealById(String name){
        view.showLoading();

        Utils.getApi().hetMealsByName(name).enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {
                view.hideLoading();
                Log.d("mealdetails", response.body().getMeals().toString());
                if(response.body()!=null && response.isSuccessful()){
                    view.setMeal(response.body().getMeals().get(0));
                }else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getMessage());
            }
        });
    }

}
