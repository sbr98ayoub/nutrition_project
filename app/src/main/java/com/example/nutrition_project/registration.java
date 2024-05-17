package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends AppCompatActivity {


    EditText username ;
    EditText email ;
    EditText password;
    EditText passwordConfirmation ;
    Button registrationButton ;
    TextView signInText;
    DbHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username =                findViewById(R.id.username);
        email    =                findViewById(R.id.Email);
        password =                findViewById(R.id.password);
        passwordConfirmation=     findViewById(R.id.confirmPassword);
        registrationButton = findViewById(R.id.registrationButton);
        signInText= findViewById(R.id.signinText);
        dbHelper = new DbHelper(this);


        // ajout du listner du button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user , pwd , rePwd , mail;
                user =username.getText().toString();
                pwd = password.getText().toString();
                mail=email.getText().toString();
                rePwd=passwordConfirmation.getText().toString();
                if(user.equals("")   ||  pwd.equals("")  || rePwd.equals("")){
                    Toast.makeText(registration.this,"veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pwd.equals(rePwd)){
                        if(dbHelper.checkCredentials(user,pwd)){
                            Toast.makeText(registration.this,"cet utilisateur existe deja",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // enregistrer les données dans la base de donnéés
                        boolean registerSucess=dbHelper.insertDataUser(user,mail,pwd);
                        if(registerSucess){
                            Toast.makeText(registration.this,"registration success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registration.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(registration.this,"registration failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(registration.this,"mot de passe non identiques",Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });







        // ajouter le listner du texte signIn
        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this,MainActivity.class );
                startActivity(intent);
            }
        });



    }
}