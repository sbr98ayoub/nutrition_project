package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    TextView redirectionText ;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        redirectionText = findViewById(R.id.signupText);
        dbHelper=new DbHelper(this);

        // ajout de listner pour le button login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pwd;
                user=username.getText().toString();
                pwd=password.getText().toString();
                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(MainActivity.this, "veuillez remplir tous les champs ", Toast.LENGTH_SHORT).show();

                } else {
                    if(dbHelper.checkCredentials(user,pwd)) {
                        Toast.makeText(MainActivity.this, "bienvenu!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, home.class);
                        intent.putExtra("USERNAME",user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "utlisateur n'existe pas !", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });

        // le listener du view
        redirectionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, registration.class);
                startActivity(intent1);
            }
        });
    }
}