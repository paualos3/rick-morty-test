package com.example.paualos3.rick_morty_test.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class CharacterResponse implements Serializable {

    private ArrayList<CharacterModel> results;

    private InfoModel info;

    public ArrayList<CharacterModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<CharacterModel> results) {
        this.results = results;
    }
}
