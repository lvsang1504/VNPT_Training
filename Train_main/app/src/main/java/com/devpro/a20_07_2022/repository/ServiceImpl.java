package com.devpro.a20_07_2022.repository;


import android.util.Log;

import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.models.auth.AccessToken;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.user.UserLogin;
import com.devpro.a20_07_2022.models.user.UserResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceImpl {


    BaseResponseListener listener;
    BaseService baseService;

    public ServiceImpl(BaseResponseListener baseResponseListener) {
        this.listener = baseResponseListener;
    }

    public void getCategory() {
//        Call<CategoryResponse> call = baseService.getIService().categoryResponseCall();
        Call<CategoryResponse> call = baseService.createService(IService.class).categoryResponseCall();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (!response.isSuccessful() || response.code() != 200) {
                    listener.didError(response.code(),response.message());
                    return;
                }
                listener.didFetch(response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                listener.didError(-1,t.getMessage());
            }
        });
    }

    public void login(UserLogin userLogin) {
        Call<UserResponse> callLogin = baseService.createService(IService.class, new AccessToken()).loginUser(userLogin);
        callLogin.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            JSONObject errorMessage = jsonObject.getJSONObject("error");
                            listener.didError(response.code(),errorMessage.getString("message"));
                        } catch (JSONException e) {
                            listener.didError(response.code(),e.toString());
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.didError(response.code(),e.toString());
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                if (response.code() == 200) {
                    listener.didFetch(response.body());
                }
                Log.d("ERRRRROR", response.code() + "");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                listener.didError(100,t.getMessage());
            }
        });
    }
}
