/**
 * WorkoutManager
 * Author: Alexa Olaes
 * Description: Handles the logic for workouts.
 *              Adds, retrieves, updates, and deletes workout records
 *              and connects the UI to the database through DatabaseConnection.
 */
package com.mycompany.fitnesstrackerapp.workout;

import com.mycompany.fitnesstrackerapp.model.Workout;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class WorkoutManager {


    
    private ArrayList<Workout> workouts = new ArrayList<>();

   
    private int nextId = 1;

    
    public void addWorkout(String name, int duration, double caloriesBurned) {
        Workout w = new Workout(nextId, name, duration, caloriesBurned);
         w.setLoggedAt(LocalDateTime.now());
         workouts.add(w);
         nextId++;
    }

   
    public ArrayList<Workout> getAllWorkouts() {
        return workouts;
    }

    
    public boolean deleteWorkout(int workoutId) {
        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getWorkoutId() == workoutId) {
                workouts.remove(i);
                return true;      
            }
        }
        return false;             
    }

 
    public double getTotalCaloriesBurned() {
        double total = 0;
        for (Workout w : workouts) {
            total = total + w.getCaloriesBurned();
        }
        return total;
    }
}




