package com.devpro.a20_07_2022.repository;

import android.content.Context;
import android.util.Log;

import com.devpro.a20_07_2022.listeners.CategoryResponseListener;
import com.devpro.a20_07_2022.listeners.LoginResponseListener;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class RequestManager {
    CategoryResponseListener listener;

    public RequestManager(CategoryResponseListener listener) {
        this.listener = listener;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://modelfashion.store")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getCategories() {
        CallCategories callCategories = retrofit.create(CallCategories.class);
        Call<CategoryResponse> call = callCategories.categoryResponseCall();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }


    public void getLogin(LoginResponseListener listener, UserLogin userLogin) {
        CallLogin callLogin = retrofit1.create(CallLogin.class);
        Call<UserResponse> call = callLogin.loginUser(userLogin);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            JSONObject errorMessage = jsonObject.getJSONObject("error");
                            listener.didError(errorMessage.getString("message"));
                        } catch (JSONException e) {
                            listener.didError(e.toString());
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.didError(e.toString());
                            e.printStackTrace();
                        }
                    }
                    return;
                }

                if (response.code() == 200) {
                    listener.didFetch(response.body(), response.message(), "token");
                }

                Log.d("ERRRRROR", response.code() + "");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////


    private interface CallCategories {
        @Headers({"Content-Type: application/json"})
        @GET("/category/getAll")
        Call<CategoryResponse> categoryResponseCall();
    }

    private interface CallLogin {
        @POST("/api/login")
        Call<UserResponse> loginUser(@Body UserLogin userLogin);
    }

}
