package com.devpro.a20_07_2022.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.activities.ProductActivity;
import com.devpro.a20_07_2022.activities.TestBroadcastActivity;
import com.devpro.a20_07_2022.adapters.BillAdapter;
import com.devpro.a20_07_2022.adapters.ProductAdapter;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.listeners.BillClickListener;
import com.devpro.a20_07_2022.listeners.ProductClickListener;
import com.devpro.a20_07_2022.models.bill.Bill;
import com.devpro.a20_07_2022.models.category.Category;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.models.product.Product;
import com.devpro.a20_07_2022.models.product.ProductResponse;
import com.devpro.a20_07_2022.repository.ServiceImpl;
import com.devpro.a20_07_2022.utils.PreferenceManager;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private PreferenceManager preferenceManager;
    ServiceImpl service;
    RecyclerView recycle_bill_list;
    BillAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        // Inflate the layout for this fragment

        preferenceManager = new PreferenceManager(getContext());
        recycle_bill_list = view.findViewById(R.id.recycle_bill_list);

        service = new ServiceImpl(baseResponseListener);
        service.getAllBill("USER1");

        return view;
    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            Log.d("BILL", response.toString());
            List<Bill> bills = (List<Bill>) response;

            Log.d("BILL1", bills.get(0).product_name);

            recycle_bill_list.setHasFixedSize(true);
            recycle_bill_list.setNestedScrollingEnabled(false);
            recycle_bill_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            adapter = new BillAdapter(getContext(), bills, billClickListener);
            recycle_bill_list.setAdapter(adapter);

        }

        @Override
        public void didError(int code, String message) {
            Log.d("AAAAAA", message);
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    };

    BillClickListener billClickListener = new BillClickListener() {

        @Override
        public void onBillClicked(String id) {

        }
    };
}