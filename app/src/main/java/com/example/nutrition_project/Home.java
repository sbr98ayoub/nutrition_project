package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    TextView welcomeText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeText=findViewById(R.id.nameUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("USERNAME");
        welcomeText.setText(user);
    }
}