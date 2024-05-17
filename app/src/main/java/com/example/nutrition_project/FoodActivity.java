package com.example.nutrition_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.widget.SearchView;

public class FoodActivity extends AppCompatActivity {

    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private FoodAdapter adapter;
    private TextView totalProteinTextView, totalCarbsTextView, totalCaloriesTextView;
    private DbHelper dbHelper;

    private Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);
        dbHelper = new DbHelper(this);

        dbHelper.insertData("Apple", 0.5, 10.0, 52.0);
        dbHelper.insertData("Banana", 1.0, 27.0, 105.0);
        dbHelper.insertData("Chicken Breast", 25.0, 0.0, 130.0);
        dbHelper.insertData("Broccoli", 2.8, 6.0, 31.0);
        dbHelper.insertData("Salmon Fillet", 20.0, 0.0, 220.0);
        dbHelper.insertData("White Rice (Cooked)", 2.0, 28.0, 130.0);
        dbHelper.insertData("Oatmeal", 6.0, 25.0, 150.0);
        dbHelper.insertData("Eggs (Large)", 6.0, 1.0, 70.0);
        dbHelper.insertData("Avocado", 3.0, 9.0, 160.0);
        dbHelper.insertData("Almonds", 6.0, 6.0, 80.0);
        dbHelper.insertData("Greek Yogurt", 10.0, 4.0, 100.0);
        dbHelper.insertData("Blueberries", 1.0, 21.0, 85.0);
        dbHelper.insertData("Spinach", 0.9, 3.6, 23.0);
        dbHelper.insertData("Sweet Potato", 2.0, 20.0, 90.0);
        dbHelper.insertData("Chicken Thighs (Boneless, Skinless)", 13.0, 0.0, 130.0);
        dbHelper.insertData("Ground Beef (80% Lean)", 18.0, 0.0, 230.0);
        dbHelper.insertData("Whole Wheat Bread", 4.0, 12.0, 80.0);
        dbHelper.insertData("Pasta (Cooked)", 7.0, 43.0, 200.0);
        dbHelper.insertData("Black Beans (Cooked)", 15.0, 41.0, 227.0);
        dbHelper.insertData("Cheddar Cheese", 7.0, 1.3, 110.0);
        dbHelper.insertData("Ground Turkey", 22.0, 0.0, 120.0);
        dbHelper.insertData("Lentils (Cooked)", 9.0, 20.0, 230.0);
        dbHelper.insertData("Quinoa (Cooked)", 8.0, 39.0, 222.0);
        dbHelper.insertData("Cottage Cheese", 15.0, 3.4, 110.0);
        dbHelper.insertData("Banana Bread", 3.0, 32.0, 157.0);
        dbHelper.insertData("Cheeseburger", 25.0, 30.0, 350.0);
        dbHelper.insertData("Chicken Caesar Salad", 30.0, 10.0, 350.0);
        dbHelper.insertData("Fried Chicken Sandwich", 22.0, 42.0, 500.0);
        dbHelper.insertData("Macaroni and Cheese", 14.0, 40.0, 330.0);
        dbHelper.insertData("Pepperoni Pizza", 12.0, 30.0, 298.0);
        dbHelper.insertData("Sushi Roll (California)", 9.0, 38.0, 255.0);
        dbHelper.insertData("Chocolate Chip Cookie", 2.0, 25.0, 210.0);
        dbHelper.insertData("Ice Cream (Vanilla)", 4.0, 24.0, 137.0);
        dbHelper.insertData("Popcorn (Buttered)", 3.6, 17.0, 93.0);
        dbHelper.insertData("Potato Chips", 2.0, 15.0, 153.0);
        dbHelper.insertData("Soda (Cola)", 0.0, 27.0, 105.0);
        dbHelper.insertData("Beer (Regular)", 1.6, 13.0, 153.0);
        dbHelper.insertData("Wine (Red)", 0.1, 2.6, 125.0);
        dbHelper.insertData("Wine (white)", 0.1, 2.6, 125.0);
        totalProteinTextView = findViewById(R.id.total_protein);
        totalCarbsTextView = findViewById(R.id.total_carbs);
        totalCaloriesTextView = findViewById(R.id.total_calories);

        ListView listView = findViewById(R.id.list_view);
        SearchView searchView = findViewById(R.id.search_view);
//        View headerView = getLayoutInflater().inflate(R.layout.header_layout, null);

// Add the headerView to your ListView
//        listView.addHeaderView(headerView);

        foodItems.addAll(dbHelper.getAllFoodItems());
        adapter = new FoodAdapter(foodItems);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("FoodActivity", "onQueryTextSubmit: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("FoodActivity", "onQueryTextChange: " + newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        adapter.notifyDataSetChanged();
        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotals();
            }
        });

        // Load food items from database

        adapter.notifyDataSetChanged();

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTotals();
            }
        });

    }

    private void resetTotals() {
        totalProteinTextView.setText("Total Protein: 0g");
        totalCarbsTextView.setText("Total Carbs: 0g");
        totalCaloriesTextView.setText("Total Calories: 0kcal");
        for (FoodItem item : foodItems) {
            item.setQuantity(0);
        }

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
    }

    private void calculateTotals() {
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalCalories = 0;
        for (FoodItem item : foodItems) {
            totalProtein += item.getProtein() * item.getQuantity();
            totalCarbs += item.getCarbs() * item.getQuantity();
            totalCalories += item.getCalories() * item.getQuantity();
        }
        totalProteinTextView.setText("Total Protein: " + totalProtein + "g");
        totalCarbsTextView.setText("Total Carbs: " + totalCarbs + "g");
        totalCaloriesTextView.setText("Total Calories: " + totalCalories + "kcal");
    }
    private class FoodAdapter extends ArrayAdapter<FoodItem> implements Filterable {
        private ArrayList<FoodItem> items;
        private ArrayList<FoodItem> filteredItems;

        public FoodAdapter(ArrayList<FoodItem> items) {
            super(FoodActivity.this, 0, items);
            this.items = items;
            this.filteredItems = new ArrayList<>(items); // Initialize with all items
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    Log.d("FoodAdapter", "performFiltering: Filtering started");
                    FilterResults results = new FilterResults();
                    ArrayList<FoodItem> filteredList = new ArrayList<>();

                    String filterPattern = constraint.toString().toLowerCase().trim();

                    if (filterPattern.isEmpty()) {
                        filteredList.addAll(items);
                    } else {
                        for (FoodItem item : items) {
                            if (item.getName().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }
                    }

                    results.values = filteredList;
                    results.count = filteredList.size();
                    Log.d("FoodAdapter", "performFiltering: Filtered list size: " + results.count);
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    Log.d("FoodAdapter", "publishResults: Publishing results");
                    filteredItems.clear();
                    filteredItems.addAll((ArrayList<FoodItem>) results.values);
                    notifyDataSetChanged();
                }
            };
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_food, parent, false);
            }

            final FoodItem item = getItem(position);
            if (item != null) {
                TextView nameTextView = convertView.findViewById(R.id.name_text_view);
                final TextView quantityTextView = convertView.findViewById(R.id.quantity_text_view);
                Button addButton = convertView.findViewById(R.id.add_button);

                nameTextView.setText(item.getName());
                quantityTextView.setText(String.valueOf(item.getQuantity()));

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quantity = item.getQuantity();
                        item.setQuantity(quantity + 1);
                        quantityTextView.setText(String.valueOf(item.getQuantity()));
                    }
                });
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredItems.size(); // Return the size of filteredItems
        }

        @Override
        public FoodItem getItem(int position) {
            return filteredItems.get(position); // Get item from filteredItems
        }
    }

}
