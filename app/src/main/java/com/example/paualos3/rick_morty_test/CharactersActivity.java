package com.example.paualos3.rick_morty_test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.paualos3.rick_morty_test.Interfaces.APIService;
import com.example.paualos3.rick_morty_test.Models.CharacterResponse;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharactersActivity extends AppCompatActivity {

    private static String TAG = "CharactersResponse";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loadData();
    }

    private void loadData() {
        APIService apiService = retrofit.create(APIService.class);
        Call<CharacterResponse> characterResponseCall = apiService.getCharactersList();
        characterResponseCall.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()){
                    CharacterResponse characterResponse = response.body();
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
