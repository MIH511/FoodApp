package com.example.foodapp.view.category;

import com.example.foodapp.Utils;
import com.example.foodapp.models.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    void getMealsByCategory(String category){

        view.showLoading();

        Call<Meals> mealsCall= Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                view.hideLoading();
                if(response.body()!=null){

                    view.setMeal(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getMessage());
            }
        });
    }
}
