package com.devpro.a20_07_2022.listeners;

import com.devpro.a20_07_2022.models.user.UserResponse;

public interface LoginResponseListener {
    void didFetch(UserResponse userResponse, String message, String cookie);
    void didError(String message);
}
