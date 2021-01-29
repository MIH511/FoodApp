package com.example.foodapp.view.details;

import com.example.foodapp.models.Meals;

import java.util.List;

public interface DetalisView {

    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
