package com.mycompany.fitnesstrackerapp.model;

import java.time.LocalDateTime;

public class Workout {
    private int workoutId;
    private String name;
    private int duration;
    private double caloriesBurned;
    private LocalDateTime loggedAt;

    public Workout() {
    }

    public Workout(int workoutId, String name, int duration, double caloriesBurned) {
        this.workoutId = workoutId;
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public int getWorkoutId() { return workoutId; }
    public void setWorkoutId(int workoutId) { this.workoutId = workoutId; }
    
    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(double caloriesBurned) { this.caloriesBurned = caloriesBurned; }
}