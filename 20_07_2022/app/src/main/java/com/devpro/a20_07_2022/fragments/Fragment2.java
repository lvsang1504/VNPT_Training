package com.devpro.a20_07_2022.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.devpro.a20_07_2022.OnClickItemTab1;
import com.devpro.a20_07_2022.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements OnClickItemTab1 {
    TextView mTextViewTab2;
    OnClickItemTab1 onClickItemTab1 = this;

    public Fragment2() {
        // Required empty public constructor
    }

    public OnClickItemTab1 getOnClickItemTab1() {
        return onClickItemTab1;
    }

    public static Fragment2 getInstance(){
        return new Fragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        mTextViewTab2 = v.findViewById(R.id.tvTab2);
        return v;
    }


    @Override
    public void onClickItem(String text) {
        mTextViewTab2.setText(text);
    }
}