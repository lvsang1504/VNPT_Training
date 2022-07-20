package com.devpro.a20_07_2022.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.a20_07_2022.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeFragment extends Fragment {

    TextView textView, textView2, textView3, textView4, textView5;
    RoundedImageView imageProfile;
    SearchView searchView;
    AlertDialog dialog;
    TabLayout tabLayout;

    Animation anim_from_button, anim_from_top, anim_from_left, from_right;

    BottomNavigationView navBar;



    String cookie;
    int idCategory = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        navBar  = getActivity().findViewById(R.id.bottom_navigation);


        getViews(view);


        //Load Animations
        anim_from_button = AnimationUtils.loadAnimation(getContext(), R.anim.anim_from_bottom);
        anim_from_top = AnimationUtils.loadAnimation(getContext(), R.anim.anim_from_top);
        anim_from_left = AnimationUtils.loadAnimation(getContext(), R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(getContext(), R.anim.anim_from_right);

        textView.setAnimation(anim_from_top);
        textView2.setAnimation(anim_from_top);
        textView3.setAnimation(anim_from_top);
        textView4.setAnimation(anim_from_top);
        textView5.setAnimation(anim_from_top);
        searchView.setAnimation(anim_from_left);
        tabLayout.setAnimation(from_right);


        //Hide status bar and navigation bar at the bottom
        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getActivity().getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        );

        return view;
    }


    private void getViews(View view) {
        textView = view.findViewById(R.id.firstText);
        textView2 = view.findViewById(R.id.textView);
        textView3 = view.findViewById(R.id.textView2);
        textView4 = view.findViewById(R.id.textView3);
        textView5 = view.findViewById(R.id.textView4);
        searchView = view.findViewById(R.id.searchView);
        imageProfile = view.findViewById(R.id.imageProfile);
        tabLayout = view.findViewById(R.id.tabLayout);

    }

}