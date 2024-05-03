package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Food extends AppCompatActivity {

    Button backButton ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        backButton =findViewById(R.id.backButton);
        Intent intent = getIntent();
        String user = intent.getStringExtra("USERNAME");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this,Home.class);
                intent.putExtra("USERNAME",user);
                startActivity(intent);
            }
        });
    }
}