package com.example.gallerypro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gallerypro.Adapter.ImageAdapter;
import com.example.gallerypro.Data.Api;
import com.example.gallerypro.Data.RetroClass;
import com.example.gallerypro.ModelClass.ImageModel;

import com.example.gallerypro.Search.Search_Activity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_Activity extends AppCompatActivity {

    private DrawerLayout draw_navig_layout;
    private NavigationView  navig_view;
    private ImageView navig_btn;
    private RecyclerView ImageRecyclerView;
    private List<ImageModel> imageModelList;

    private CardView search_box;

    private ImageAdapter imageAdapter;
    private ProgressBar recyclerPBar;
    private NestedScrollView nestedScrollView;

    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);


        navig_view = findViewById(R.id.navig_view);
        draw_navig_layout= findViewById(R.id.draw_navig_layout);
        navig_btn=findViewById(R.id.navig_btn);
        recyclerPBar=findViewById(R.id.recyclerPBar);
        nestedScrollView=findViewById(R.id.scroll_view);
        search_box=findViewById(R.id.search_box);

        ImageRecyclerView=findViewById(R.id.ImageRecyclerView);

        //search
        search_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Activity.this, Search_Activity.class);
                startActivity(intent);
            }
        });

        // navigation bar
        navig_view.bringToFront();
        navig_view.setCheckedItem(R.id.HomeNId);

        navig_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ShareNId:
                    {
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Amiyaranjan12/"));
                        startActivity(intent);

                    }



                }

                return true;
            }
        });


        navig_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navig_function navigFunction=new navig_function();
                navigFunction.start();

            }
        });

        //RecyclerView


        LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        ImageRecyclerView.setLayoutManager(layoutManager);



        setData(page);
        //swipe


        //nested
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY== v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){

                    recyclerPBar.setVisibility(View.VISIBLE);
                    page++;

                    setData(page);

                    //  Toast.makeText(getApplicationContext(),String.valueOf(page),Toast.LENGTH_SHORT).show();

                }
            }
        });
       //check internet

        if(!checkInternet()){

            Snackbar snackbar=Snackbar.make(draw_navig_layout,"Check Internet..",Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
            snackbar.setActionTextColor(Color.CYAN);
            snackbar.show();


        }




    }

    private void setData(int a){

        Api api= RetroClass.getRetrofit().create(Api.class);
        Call<ImageModel> setData=api.getImageList(a);

        setData.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if (response.isSuccessful()){

                    recyclerPBar.setVisibility(View.GONE);
                   // Toast.makeText(getApplicationContext(),"goood",Toast.LENGTH_SHORT).show();
                   imageAdapter=new ImageAdapter(response.body().getPhotos().getPhoto());
                   ImageRecyclerView.setAdapter(imageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {


            }
        });

    }

    class navig_function extends Thread{
                @Override
                public void run() {

                    if(draw_navig_layout.isDrawerVisible(GravityCompat.START)){

                        draw_navig_layout.closeDrawer(GravityCompat.START);
                    }
                    else {
                        draw_navig_layout.openDrawer(GravityCompat.START);

                    }


                }
            }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) Home_Activity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}