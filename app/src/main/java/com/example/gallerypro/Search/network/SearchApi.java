package com.example.gallerypro.Search.network;

import com.example.gallerypro.ModelClass.ImageModel;
import com.example.gallerypro.Search.OtherClass.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s&text=cat")
    Call<SearchModel> getSearch(
            @Query("text") String searchQuery
    );
}
