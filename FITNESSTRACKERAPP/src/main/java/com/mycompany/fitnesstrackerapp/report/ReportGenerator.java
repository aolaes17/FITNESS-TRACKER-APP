package com.mycompany.fitnesstrackerapp.report;

import com.mycompany.fitnesstrackerapp.model.Meal;
import com.mycompany.fitnesstrackerapp.model.User;
import com.mycompany.fitnesstrackerapp.model.Workout;
import com.mycompany.fitnesstrackerapp.profile.UserProfile;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportGenerator {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");

    public static String generateReport(User user, UserProfile.Goals goals) {
        Workout[] workouts = user.getWorkouts();
        Meal[] meals = user.getMeals();

        int workoutCount = 0;
        int totalMinutes = 0;
        double caloriesBurned = 0;
        for (Workout w : workouts) {
            if (w == null) {
                continue;
            }
            workoutCount++;
            totalMinutes += w.getDuration();
            caloriesBurned += w.getCaloriesBurned();
        }

        int mealCount = 0;
        double caloriesConsumed = 0;
        for (Meal m : meals) {
            if (m == null) {
                continue;
            }
            mealCount++;
            caloriesConsumed += m.getCalories();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("FITNESS PROGRESS REPORT\n");
        sb.append("=======================\n");
        sb.append("Name: ").append(user.getName()).append("\n");
        sb.append("Username: ").append(user.getUsername()).append("\n\n");

        sb.append("SUMMARY\n");
        sb.append("Total Workouts: ").append(workoutCount).append("\n");
        sb.append("Total Workout Minutes: ").append(totalMinutes).append("\n");
        sb.append(String.format("Calories Burned: %.1f kcal%n", caloriesBurned));
        sb.append("Total Meals: ").append(mealCount).append("\n");
        sb.append(String.format("Calories Consumed: %.1f kcal%n", caloriesConsumed));
        sb.append(String.format("Net Calories: %.1f kcal%n%n", caloriesConsumed - caloriesBurned));

        sb.append("GOALS\n");
        if (goals == null) {
            sb.append("No goals set.\n\n");
        } else {
            sb.append(String.format("Daily Calorie Intake: %.0f kcal%n", goals.getDailyCalorieIntake()));
            sb.append(String.format("Daily Calorie Burn: %.0f kcal%n", goals.getDailyCalorieBurn()));
            sb.append("Weekly Workout Minutes: ").append(goals.getWeeklyWorkoutMinutes()).append("\n\n");
        }

        sb.append("WORKOUT HISTORY\n");
        if (workoutCount == 0) {
            sb.append("No workouts logged.\n");
        }
        for (Workout w : workouts) {
            if (w == null) {
                continue;
            }
            sb.append(formatDate(w.getLoggedAt())).append(" | ")
              .append(w.getName()).append(" | ")
              .append(w.getDuration()).append(" min | ")
              .append(w.getCaloriesBurned()).append(" kcal\n");
        }

        sb.append("\nMEAL HISTORY\n");
        if (mealCount == 0) {
            sb.append("No meals logged.\n");
        }
        for (Meal m : meals) {
            if (m == null) {
                continue;
            }
            sb.append(formatDate(m.getLoggedAt())).append(" | ")
              .append(m.getName()).append(" | ")
              .append(m.getCalories()).append(" kcal | ")
              .append(m.getNutritionalInfo()).append("\n");
        }

        return sb.toString();
    }

    public static void saveReport(String report, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(report);
        }
    }

    private static String formatDate(LocalDateTime date) {
        if (date == null) {
            return "-";
        }
        return date.format(FORMAT);
    }
}