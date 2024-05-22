package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Workout extends AppCompatActivity {
    private Button returnButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Intent intent=getIntent();
        String username=intent.getStringExtra("USERNAME");
        returnButton=findViewById(R.id.returnToHome);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Workout.this,home.class);
                intent.putExtra("USERNAME",username);
                startActivity(intent);
            }
        });
    }
}