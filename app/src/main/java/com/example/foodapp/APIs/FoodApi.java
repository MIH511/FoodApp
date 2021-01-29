/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.foodapp.APIs;

import com.example.foodapp.models.Categories;
import com.example.foodapp.models.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("random.php")
    Call<Meals> getMeal();

    @GET ("categories.php")
    Call<Categories> getGategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String category);

        @GET("search.php")
    Call<Meals> hetMealsByName(@Query("s") String mealName);
}
