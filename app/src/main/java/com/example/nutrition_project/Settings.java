package com.example.nutrition_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private EditText etOldPassword, etNewPassword, etConfirmNewPassword;
    private Button btnChangePassword, btnCancel;
    private DbHelper dbHelper;
    private String currentUsername; // Assume this is passed from the previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnCancel = findViewById(R.id.btnCancel);
        dbHelper = new DbHelper(this);

        // Retrieve the username from the Intent extras
        Intent intent = getIntent();
        currentUsername = intent.getStringExtra("USERNAME");

        if (currentUsername == null) {
            // Handle the case where the username was not passed
            Toast.makeText(this, "Username not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
            return;
        }

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainPage();
            }
        });
    }

    private void changePassword() {
        String oldPassword = etOldPassword.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmNewPassword = etConfirmNewPassword.getText().toString().trim();

        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmNewPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.checkCredentials(currentUsername, oldPassword)) {
            boolean isUpdated = dbHelper.updateUserPassword(currentUsername, newPassword);
            if (isUpdated) {
                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Password update failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private void returnToMainPage() {
        Intent intent = new Intent(Settings.this, home.class);
        intent.putExtra("USERNAME", currentUsername);
        startActivity(intent);
        finish();
    }
}
