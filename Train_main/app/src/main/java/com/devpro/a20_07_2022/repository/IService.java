package com.devpro.a20_07_2022.repository;

import com.devpro.a20_07_2022.models.category.Category;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IService {
    @Headers({"Content-Type: application/json"})
    @GET("/category/getAll")
    Call<CategoryResponse> categoryResponseCall();

    @POST("/api/login")
    Call<UserResponse> loginUser(@Body UserLogin userLogin);

}
