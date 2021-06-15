package com.example.gallerypro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.gallerypro.R;
import com.example.gallerypro.FullImage_Activity;
import com.example.gallerypro.ModelClass.ImageModel;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.imageViewHolder> {

    private Context context;

    private List<ImageModel.Photos.Photo> image_list;
    public ImageAdapter(List<ImageModel.Photos.Photo> image_list){

        this.image_list=image_list;

    }

    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row,parent,false);
       imageViewHolder viewHolder=new imageViewHolder(view);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull imageViewHolder holder, int position) {
        context=holder.itemView.getContext();

        Glide.with(context).load(this.image_list.get(position).getUrlS()).apply(RequestOptions.centerCropTransform())
                .into(holder.image_view);

    }

    @Override
    public int getItemCount() {
        if (this.image_list !=null){
            return image_list.size();
        }
       return 0;
    }

    public class imageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image_view;
        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int imagePosition=getAdapterPosition();
            Intent intent=new Intent(context, FullImage_Activity.class);

            intent.putExtra("imageUrl",image_list.get(imagePosition).getUrlS());
            context.startActivity(intent);

        }
    }
}
