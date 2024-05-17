package com.example.nutrition_project;

public class FoodItem {
    private String name;
    private double protein;
    private double carbs;
    private double calories;
    private int quantity;

    public FoodItem(String name, double protein, double carbs, double calories) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getCalories() {
        return calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

