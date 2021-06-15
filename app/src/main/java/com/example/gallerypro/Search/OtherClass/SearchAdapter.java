package com.example.gallerypro.Search.OtherClass;

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
import com.example.gallerypro.FullImage_Activity;
import com.example.gallerypro.ModelClass.ImageModel;
import com.example.gallerypro.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;

    private List<SearchModel.Photos.Photo> searchimage_list;
    public SearchAdapter(List<SearchModel.Photos.Photo> searchimage_list){

        this.searchimage_list=searchimage_list;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row,parent,false);
        SearchViewHolder viewHolder=new SearchViewHolder(view);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        context=holder.itemView.getContext();


        Glide.with(context).load(this.searchimage_list.get(position).getUrlS()).apply(RequestOptions.centerCropTransform())
                .into(holder.image_view);

    }

    @Override
    public int getItemCount() {
        if (this.searchimage_list !=null){
            return searchimage_list.size();
        }
       return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image_view;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int imagePosition=getAdapterPosition();
            Intent intent=new Intent(context, FullImage_Activity.class);

            intent.putExtra("imageUrl",searchimage_list.get(imagePosition).getUrlS());
            context.startActivity(intent);

        }
    }
}
