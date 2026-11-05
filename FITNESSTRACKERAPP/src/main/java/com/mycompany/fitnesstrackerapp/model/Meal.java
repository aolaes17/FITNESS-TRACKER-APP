package com.mycompany.fitnesstrackerapp.model;

import java.time.LocalDateTime;

public class Meal {
    private int mealId;
    private String name;
    private double calories;
    private String nutritionalInfo;
    private LocalDateTime loggedAt;

    public Meal() {
    }

    public Meal(int mealId, String name, double calories, String nutritionalInfo) {
        this.mealId = mealId;
        this.name = name;
        this.calories = calories;
        this.nutritionalInfo = nutritionalInfo;
    }

    public int getMealId() { return mealId; }
    public void setMealId(int mealId) { this.mealId = mealId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public String getNutritionalInfo() { return nutritionalInfo; }
    public void setNutritionalInfo(String nutritionalInfo) { this.nutritionalInfo = nutritionalInfo; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}