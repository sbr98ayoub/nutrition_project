package com.example.nutrition_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    ImageView x1;
    ImageView x2;
    ImageView x3;
    Button x4;
    ImageView settingsButton;

    Button workoutButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        if (username != null) {
            TextView welcomeText = findViewById(R.id.nameUser);
            welcomeText.setText("Welcome, " + username + "!");
        }
        x1=findViewById(R.id.tswira3);
        x2=findViewById(R.id.tswira2);
        x3=findViewById(R.id.tswira1);
        x4=findViewById(R.id.mealButton2);
        workoutButton=findViewById(R.id.workoutButton);
        settingsButton=findViewById(R.id.tswira3);


        x2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent = new Intent(home.this, Objective.class);
                                      startActivity(intent);
                                  }
                              }


        );
        x3.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent = new Intent(home.this, FoodActivity.class);
                                      startActivity(intent);
                                  }
                              }


        );
        x4.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent intent = new Intent(home.this, Meals.class);
                                      startActivity(intent);
                                  }
                              }




        );

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home.this,Workout.class);
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,Settings.class);
                intent.putExtra("USERNAME",username);
                startActivity(intent);
            }
        });


    }
}