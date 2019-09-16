package com.example.paualos3.rick_morty_test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.paualos3.rick_morty_test.Adapters.MyListAdapter;
import com.example.paualos3.rick_morty_test.Interfaces.APIService;
import com.example.paualos3.rick_morty_test.Models.*;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharactersActivity extends AppCompatActivity {

    private static String TAG = "CharactersResponse";
    private Retrofit retrofit;

    private boolean loadMore;
    private int page;
    private RecyclerView recyclerView;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listAdapter = new MyListAdapter(this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (loadMore) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Final reached.");
                            ++page;
                            loadData(page);
                        }
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        page = 1;
        loadMore = true;
        loadData(page);
    }

    private void loadData(int page) {
        APIService apiService = retrofit.create(APIService.class);
        Call<CharacterResponse> characterResponseCall = apiService.getCharactersList(page);
        characterResponseCall.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()){
                    CharacterResponse characterResponse = response.body();
                    ArrayList<CharacterModel> characters = characterResponse.getResults();
                    listAdapter.addCharactersList(characters);
                } else {
                    Log.e(TAG, "onResponse error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

}
