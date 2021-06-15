package com.example.gallerypro.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.gallerypro.Home_Activity;
import com.example.gallerypro.R;
import com.example.gallerypro.Search.OtherClass.SearchAdapter;
import com.example.gallerypro.Search.OtherClass.SearchModel;
import com.example.gallerypro.Search.network.SearchApi;
import com.example.gallerypro.Search.network.SearchRetro;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Activity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private SearchAdapter adapter;
    private SearchView searchView;
    private ImageView navig_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        searchRecyclerView=findViewById(R.id.searchRecyclerView);
        searchView=findViewById(R.id.searchView);
        navig_btn=findViewById(R.id.navig_btn);



        LinearLayoutManager layoutManager=new GridLayoutManager(this,2);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchView.setBackgroundColor(Color.TRANSPARENT);

        //back
        navig_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search_Activity.this, Home_Activity.class);
                startActivity(intent);

            }
        });

        //

        searchResult();

    }

    private void searchResult(){
        searchfunction(searchView)
                .debounce(280, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) {
                        if (text.isEmpty()) {
                            searchData("");;
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String query) {
                        return check(query)

                                .doOnError(throwable -> {
                                })
                                .onErrorReturn(throwable -> "");


                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


                .subscribe(result -> {
                    System.out.println(result);
                    searchData(result);
                });
    }

    private void searchData(String searchText){

        SearchApi searchApi= SearchRetro.getRetrofitData().create(SearchApi.class);
        Call<SearchModel> searchData=searchApi.getSearch(searchText);
        searchData.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()){

                    // Toast.makeText(getApplicationContext(),"goood",Toast.LENGTH_SHORT).show();
                    adapter=new SearchAdapter(response.body().getPhotos().getPhoto());
                    searchRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //




    public  Observable<String> searchfunction(SearchView searchView) {

            final PublishSubject<String> publishSubject = PublishSubject.create();


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    publishSubject.onComplete();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String text) {
                    publishSubject.onNext(text);
                    return true;
                }
            });
            return publishSubject;
    }


    private Observable<String> check(final String query) {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Boolean, String>() {
                    @Override
                    public String apply(@NonNull Boolean value) {
                        return query;
                    }
                });
    }
    //

}