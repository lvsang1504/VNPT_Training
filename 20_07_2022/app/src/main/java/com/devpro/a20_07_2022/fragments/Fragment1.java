package com.devpro.a20_07_2022.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.a20_07_2022.OnClickItemTab1;
import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.adapters.CustomAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    RecyclerView mRecyclerView;
    CustomAdapter adapter;
    ArrayList listItem = new ArrayList();
    OnClickItemTab1 onClickItemTab1;
    public Fragment1() {
        // Required empty public constructor
    }

    public void setOnClickItemTab1(OnClickItemTab1 onClickItemTab1) {
        this.onClickItemTab1 = onClickItemTab1;
    }

    public static Fragment1 getInstance(){
        return new Fragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_1, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setup();
        adapter = new CustomAdapter(listItem, getContext(), onClickItemTab1);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        return v;
    }

    private void setup() {
        for(int i=0; i<100; i++){
            listItem.add(i);
        }
    }
}