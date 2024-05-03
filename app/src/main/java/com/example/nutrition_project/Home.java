package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    LinearLayout searchFood;

    LinearLayout objectives;
    TextView welcomeText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeText=findViewById(R.id.nameUser);
        objectives=findViewById(R.id.objectives_id);
        Intent intent = getIntent();
        String user = intent.getStringExtra("USERNAME");
        welcomeText.setText("bonjour  " +user);
        searchFood = findViewById(R.id.searchfood);
        //listener pour rechercher les aliments;
        searchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Food.class);
                intent.putExtra("USERNAME",user);
                startActivity(intent);
            }
        });
        //listener pour avoir son  objectif :
        objectives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,Objective.class);
                intent.putExtra("USERNAME",user);
                startActivity(intent);
            }
        });



    }
}