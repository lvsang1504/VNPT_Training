package com.devpro.a20_07_2022.listeners;

import com.devpro.a20_07_2022.models.category.CategoryResponse;

public interface CategoryResponseListener {
    void didFetch(CategoryResponse response, String message);
    void didError(String message);
}
