package com.example.moviecatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.moviecatalog.R;
import com.example.moviecatalog.adapter.MovieAdapter;
import com.example.moviecatalog.model.Response;
import com.example.moviecatalog.model.Result;
import com.example.moviecatalog.rest.ApiClient;
import com.example.moviecatalog.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private SearchView searchView;
    String API_KEY = "a2945948a3697cef4f68307a28f9a5f5";
    String LANGUAGE = "en-US";
    String CATEGORY = "popular";
    int PAGE = 1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        callRetro();
    }

    private void callRetro() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getMovie(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> mList = response.body().getResults();
                adapter = new MovieAdapter(MainActivity.this, mList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1){
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<Response> call = apiInterface.getQuery(API_KEY,LANGUAGE,newText,PAGE);
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            List<Result> mList = response.body().getResults();
                            adapter = new MovieAdapter(MainActivity.this, mList);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            t.fillInStackTrace();
                        }
                    });
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }




}

//public class MainActivity extends AppCompatActivity {
//
//    private MovieAdapter adapter;
//    private SearchView searchView;
//    String API_KEY = "034761b5e3f463a67f5ceb809d69cd2d";
//    String LANGUAGE = "en-US";
//    String CATEGORY = "popular";
//    int PAGE = 1;
//    RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.rvMovie);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        CallRetrofit();
//    }
//
//    private void CallRetrofit() {
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<Response> call = apiInterface.getMovie(CATEGORY, API_KEY, LANGUAGE, PAGE);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                List<Result> mList = response.body().getResults();
//                adapter = new MovieAdapter(MainActivity.this, mList);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//}