package com.example.nutrition_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Objective extends AppCompatActivity {

    private  String user ;

    private EditText weightEditText;
    private EditText heightEditText;
    private EditText ageEditText;
    private TextView healthStatusTextView;
    private TextView proteinNeedsTextView;
    private TextView calorieNeedsTextView;

    private ImageView imageView;

    private Button returnButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective);
        Intent intent = getIntent();
        user = intent.getStringExtra("USERNAME");
        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);
        ageEditText = findViewById(R.id.age);
        healthStatusTextView = findViewById(R.id.healthStatusTextView);
        proteinNeedsTextView = findViewById(R.id.proteinNeedsTextView);
        calorieNeedsTextView = findViewById(R.id.calorieNeedsTextView);
        imageView= findViewById(R.id.imageView3);
        returnButton= findViewById(R.id.returnButton);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResults();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Objective.this,Home.class);
                intent.putExtra("USERNAME",user);
                startActivity(intent);
            }
        });
    }

    private void calculateAndDisplayResults() {
        // Get user input
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        int age = Integer.parseInt(ageEditText.getText().toString());

        // Calculate BMI (Body Mass Index)
        double bmi = calculateBMI(weight, height);
        Log.d("BMI", "BMI: " + bmi);

        // Determine health status based on BMI
        String healthStatus = getHealthStatus(bmi);
        healthStatusTextView.setText("Health Status: " + healthStatus);
        Log.d("HealthStatus", "Health Status: " + healthStatus);

        // Calculate protein and calorie needs
        double proteinNeeds = calculateProteinNeeds(weight);
        double calorieNeeds = calculateCalorieNeeds(weight, height, age);

        proteinNeedsTextView.setText("Protein Needs: " + proteinNeeds + " grams per day");
        Log.d("ProteinNeeds", "Protein Needs: " + proteinNeeds);

        calorieNeedsTextView.setText("Calorie Needs: " + calorieNeeds + " calories per day");
        Log.d("CalorieNeeds", "Calorie Needs: " + calorieNeeds);
    }


    private double calculateBMI(double weight, double height) {
        // Convert height to meters
        double heightInMeters = height / 100; // Assuming height is in centimeters
        // Calculate BMI using weight (kg) and height (m)
        return weight / (heightInMeters * heightInMeters);
    }


    private String getHealthStatus(double bmi) {
        // Determine health status based on BMI
        if (bmi < 18.5) {
            imageView.setImageResource(R.drawable.logo);
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private double calculateProteinNeeds(double weight) {
        return Double.parseDouble(new DecimalFormat("#.##").format(weight*0.8));
    }

    private double calculateCalorieNeeds(double weight, double height, int age) {
        double result = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        return Double.parseDouble(new DecimalFormat("#.##").format(result));
    }

}