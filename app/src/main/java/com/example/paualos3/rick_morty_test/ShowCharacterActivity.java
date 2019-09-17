package com.example.paualos3.rick_morty_test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.paualos3.rick_morty_test.Models.CharacterModel;
import com.google.gson.Gson;

public class ShowCharacterActivity extends AppCompatActivity {

    private CharacterModel character;
    private ImageButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_character);

        Intent i = getIntent();
        character = new Gson().fromJson(getIntent().getStringExtra("character"), CharacterModel.class);
        PopulateData();
    }

    private void PopulateData() {
        TextView nameText = (TextView) findViewById(R.id.nameText);
        TextView genderText = (TextView) findViewById(R.id.genderText);
        TextView statusText = (TextView) findViewById(R.id.statusText);
        TextView speciesText = (TextView) findViewById(R.id.speciesText);
        TextView originText = (TextView) findViewById(R.id.originText);
        ImageView photoImageView = (ImageView) findViewById(R.id.photoImageView);
        favoriteButton = (ImageButton) findViewById(R.id.favoriteImageButton);

        nameText.setText(character.getName());
        genderText.setText(character.getGender());
        statusText.setText(character.getStatus());
        speciesText.setText(character.getSpecies());
        originText.setText(character.getOrigin().getName());
        Glide.with(getApplicationContext())
                .load(character.getImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoImageView);
        if (!character.isFavorited()) {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_white_36);
            favoriteButton.setColorFilter(Color.RED);
        }
        else{
            favoriteButton.setImageResource(R.drawable.baseline_favorite_border_black_18);
            favoriteButton.setColorFilter(Color.BLACK);
        }
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character.setFavorited(!character.isFavorited());
                if (!character.isFavorited()) {
                    favoriteButton.setImageResource(R.drawable.baseline_favorite_white_36);
                    favoriteButton.setColorFilter(Color.RED);
                }
                else{
                    favoriteButton.setImageResource(R.drawable.baseline_favorite_border_black_36);
                    favoriteButton.setColorFilter(Color.BLACK);
                }
            }
        });
    }
}
