package com.devpro.a20_07_2022.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.product.ProductDetailResponse;
import com.devpro.a20_07_2022.repository.ServiceImpl;

public class ProductActivity extends AppCompatActivity {

    ServiceImpl service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String id = getIntent().getStringExtra("id_product");

        service = new ServiceImpl(baseResponseListener);

        if (id != null) {
            service.getProductDetail(id);
        }
    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            if (response instanceof ProductDetailResponse) {
                ProductDetailResponse productDetailResponse = (ProductDetailResponse) response;
                Log.d("RESPONSEEE", ((ProductDetailResponse) response).product.get(0).product_name);
            }
        }

        @Override
        public void didError(int code, String message) {
            Toast.makeText(ProductActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };

    public void backClick(View view) {
        onBackPressed();
    }
}