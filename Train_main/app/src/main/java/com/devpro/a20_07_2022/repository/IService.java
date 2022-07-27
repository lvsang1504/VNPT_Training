package com.devpro.a20_07_2022.repository;

import com.devpro.a20_07_2022.models.category.Category;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.product.ProductDetailResponse;
import com.devpro.a20_07_2022.models.product.ProductResponse;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IService {
    @Headers({"Content-Type: application/json"})
    @GET("/category/getAll")
    Call<CategoryResponse> categoryResponseCall();

    @Headers({"Content-Type: application/json"})
    @GET("/product/getAllProductByCategory")
    Call<ProductResponse> productResponseCall();

    @Headers({"Content-Type: application/json"})
    @GET("/product/detail/{id}")
    Call<ProductDetailResponse> productDetailResponseCall(@Path("id") String id);

    @POST("/api/login")
    Call<UserResponse> loginUser(@Body UserLogin userLogin);

}
