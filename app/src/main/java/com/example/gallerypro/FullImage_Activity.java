package com.example.gallerypro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FullImage_Activity extends AppCompatActivity {

    private Bundle bundle;
    private ImageView fullImageView,back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_layout);

        fullImageView=findViewById(R.id.fullImageView);
        back_btn=findViewById(R.id.back_btn);


        //bundle
        bundle=getIntent().getExtras();
        String imageUrl=bundle.getString("imageUrl");

        //image

        Glide.with(FullImage_Activity.this).load(imageUrl).apply(RequestOptions.centerCropTransform())
                .into(fullImageView);

        //back
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFunction();
            }
        });


    }


    public void backFunction(){

        Intent intent=new Intent(FullImage_Activity.this,Home_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        backFunction();
    }


    //






   
}
