package com.example.foodapp.view.category;

import com.example.foodapp.models.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}
