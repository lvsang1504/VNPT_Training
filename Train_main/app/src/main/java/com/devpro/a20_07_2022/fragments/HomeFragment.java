package com.devpro.a20_07_2022.fragments;

import android.app.AlertDialog;
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
import com.devpro.a20_07_2022.listeners.CategoryResponseListener;
import com.devpro.a20_07_2022.models.category.Category;
import com.devpro.a20_07_2022.models.category.CategoryResponse;
import com.devpro.a20_07_2022.repository.RequestManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragment extends Fragment implements CategoryResponseListener {

    TextView textView, textView2, textView3, textView4, textView5;
    RoundedImageView imageProfile;
    SearchView searchView;
    AlertDialog dialog;
    TabLayout tabLayout;
    ImageView imageView;

    Animation anim_from_button, anim_from_top, anim_from_left, from_right;

    RequestManager manager;

    BottomNavigationView navBar;


    RecyclerView recyclerView;


    int idCategory = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        navBar  = getActivity().findViewById(R.id.bottom_navigation);


        getViews(view);


        manager = new RequestManager(this);
        manager.getCategories(categoryResponseListener);



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
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(query!=""){
//                    manager.getRoomByQuery(listenerQuery,idCategory,query);
//                }
//                else{
//                    tabLayout.setVisibility(View.VISIBLE);
//                    manager.getCategories(categoryResponseListener);
//                    manager.getWishlists(wishlistListResponseListener, cookie);
//                }
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                manager.getRoomByQuery(listenerQuery,idCategory,newText);
//                return false;
//            }
//        });
    }

    private final CategoryResponseListener categoryResponseListener = new CategoryResponseListener() {
        @Override
        public void didFetch(CategoryResponse response, String message) {

            List<Category> categories = response.data;
            Log.d("AAAAAA" , categories.toString());
            for (Category category : categories
            ) {
                tabLayout.addTab(tabLayout.newTab().setText(category.category_name));
            }

//            manager.getRoomByCategory(randomRecipeResponseListener, 1);
//            dialog.show();

            Picasso.get().load(categories.get(0).category_image).into(imageView);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
//                    manager.getRoomByCategory(randomRecipeResponseListener, tab.getPosition() + 1);
//                    dialog.show();
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

        @Override
        public void didError(String message) {
            Log.d("AAAAAA" , message);

            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    };


    private void getViews(View view) {
        textView = view.findViewById(R.id.firstText);
        textView2 = view.findViewById(R.id.textView);
        textView3 = view.findViewById(R.id.textView2);
        textView4 = view.findViewById(R.id.textView3);
        textView5 = view.findViewById(R.id.textView4);
        searchView = view.findViewById(R.id.searchView);
        imageView = view.findViewById(R.id.imageView);
        imageProfile = view.findViewById(R.id.imageProfile);
        tabLayout = view.findViewById(R.id.tabLayout);


    }

    @Override
    public void didFetch(CategoryResponse response, String message) {
        /// get dtata
    }

    @Override
    public void didError(String message) {
// handle errer
    }
}