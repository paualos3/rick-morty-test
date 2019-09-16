package com.example.paualos3.rick_morty_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), R.string.app_name, Toast.LENGTH_LONG);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });*/
    }

    public void startApp(View view) {
        Intent intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);
    }
}
