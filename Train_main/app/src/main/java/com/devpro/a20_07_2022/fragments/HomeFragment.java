package com.devpro.a20_07_2022.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.activities.LoginActivity;
import com.devpro.a20_07_2022.listeners.BaseResponseListener;
import com.devpro.a20_07_2022.models.category.Category;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.repository.BaseService;
import com.devpro.a20_07_2022.repository.ServiceImpl;
import com.devpro.a20_07_2022.utils.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragment extends Fragment implements BaseResponseListener {

    TextView textView, textView2, textView3, textView4, textView5;
    RoundedImageView btnLogout;
    SearchView searchView;
    TabLayout tabLayout;
    ImageView imageView;

    Animation anim_from_button, anim_from_top, anim_from_left, from_right;

//    RequestManager manager;

    ServiceImpl service;

    BottomNavigationView navBar;


    RecyclerView recyclerView;

    PreferenceManager preferenceManager;


    int idCategory = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        navBar = getActivity().findViewById(R.id.bottom_navigation);


        getViews(view);
        preferenceManager = new PreferenceManager(getContext());


//        manager = new RequestManager(baseResponseListener);
//        manager.getCategories();
        service = new ServiceImpl(baseResponseListener);
        service.getCategory();

//        getCategories();


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
        imageView.setAnimation(anim_from_button);


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
        setEvents();

        return view;
    }

    private void setEvents() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogout();
            }
        });

    }

    private void checkLogout() {
        new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .show();
    }

    private void logout() {
        preferenceManager.clear();
        BaseService.clearRetrofit();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    private void getViews(View view) {
        textView = view.findViewById(R.id.firstText);
        textView2 = view.findViewById(R.id.textView);
        textView3 = view.findViewById(R.id.textView2);
        textView4 = view.findViewById(R.id.textView3);
        textView5 = view.findViewById(R.id.textView4);
        searchView = view.findViewById(R.id.searchView);
        imageView = view.findViewById(R.id.imageView);
        btnLogout = view.findViewById(R.id.btnLogout);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    BaseResponseListener baseResponseListener = new BaseResponseListener() {
        @Override
        public void didFetch(Object response) {
            if (response instanceof CategoryResponse) {
                CategoryResponse categoryResponse = (CategoryResponse) response;
                List<Category> categories = categoryResponse.data;
                for (Category category : categories
                ) {
                    tabLayout.addTab(tabLayout.newTab().setText(category.category_name));
                }
                Picasso.get().load(categories.get(0).category_image).into(imageView);

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        idCategory = tab.getPosition() + 1;
                        Picasso.get().load(categories.get(tab.getPosition()).category_image).into(imageView);
                        imageView.setAnimation(anim_from_button);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        }

        @Override
        public void didError(int code, String message) {
            Log.d("AAAAAA", message);
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void didFetch(Object response) {
        if (response instanceof CategoryResponse) {
            CategoryResponse categoryResponse = (CategoryResponse) response;
            List<Category> categories = categoryResponse.data;
            Log.d("AAAAAA", categories.toString());
            for (Category category : categories
            ) {
                tabLayout.addTab(tabLayout.newTab().setText(category.category_name));
            }
            Picasso.get().load(categories.get(0).category_image).into(imageView);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    idCategory = tab.getPosition() + 1;
                    Picasso.get().load(categories.get(tab.getPosition()).category_image).into(imageView);
                    imageView.setAnimation(anim_from_button);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @Override
    public void didError(int code, String message) {

    }
}