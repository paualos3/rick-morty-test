package com.example.paualos3.rick_morty_test.Interfaces;

import com.example.paualos3.rick_morty_test.Models.CharacterResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("character")
    Call<CharacterResponse> getCharactersList();
}
