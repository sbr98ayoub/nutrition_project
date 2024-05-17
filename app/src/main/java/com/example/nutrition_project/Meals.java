package com.example.nutrition_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Meals extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private ListAdapter adapter;
    private ArrayList<FoodList> heroes;

    private Button  buttonOnImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        adapter = new ListAdapter(this);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(Meals.this, heroes.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        buttonOnImage = findViewById(R.id.buttonOnImage);
        buttonOnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = getIntent();
                String username = intent.getStringExtra("USERNAME");
                Intent intent1= new Intent(Meals.this,home.class);
                intent1.putExtra("USRENAME",username);
                startActivity(intent1);
            }
        });
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        heroes = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            FoodList hero = new FoodList();
            hero.setPhoto(dataPhoto.getResourceId(i, -1));
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            heroes.add(hero);
        }
        adapter.setMeals(heroes);
    }

}
