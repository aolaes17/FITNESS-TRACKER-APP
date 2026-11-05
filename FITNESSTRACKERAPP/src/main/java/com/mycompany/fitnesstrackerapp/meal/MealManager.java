package com.mycompany.fitnesstrackerapp.meal;

import com.mycompany.fitnesstrackerapp.db.DatabaseConnection;
import com.mycompany.fitnesstrackerapp.model.Meal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealManager {

    private Meal[] meals = new Meal[100];
    private int mealCount = 0;

    public boolean saveMeal(int userId, String name, double calories, String nutritionalInfo) {

        String sql = "INSERT INTO meals (user_id, name, calories, nutritional_info) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setDouble(3, calories);
            ps.setString(4, nutritionalInfo);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Save failed: " + e.getMessage());
            return false;
        }
    }

    public void loadMeals(int userId) {
        mealCount = 0;

        String sql = "SELECT meal_id, name, calories, nutritional_info FROM meals WHERE user_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next() && mealCount < meals.length) {

                    meals[mealCount] = new Meal(
                            rs.getInt("meal_id"),
                            rs.getString("name"),
                            rs.getDouble("calories"),
                            rs.getString("nutritional_info")
                    );

                    mealCount++;
                }
            }

        } catch (SQLException e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    public int getMealCount() {
        return mealCount;
    }

    public Meal getMeal(int index) {
        if (index >= 0 && index < mealCount) {
            return meals[index];
        }
        return null;
    }

    public String getMealsAsText() {

        StringBuilder text = new StringBuilder();

        for (int i = 0; i < mealCount; i++) {
            text.append("ID: ")
                .append(meals[i].getMealId())
                .append(" | ")
                .append(meals[i].getName())
                .append(" | ")
                .append(meals[i].getCalories())
                .append(" cal | ")
                .append(meals[i].getNutritionalInfo())
                .append("\n");
        }

        return text.toString();
    }

    public void sortByCalories() {

        for (int i = 0; i < mealCount - 1; i++) {
            int min = i;

            for (int j = i + 1; j < mealCount; j++) {

                if (meals[j].getCalories() < meals[min].getCalories()) {
                    min = j;
                }
            }

            swap(i, min);
        }
    }

    public void sortByName() {

        for (int i = 0; i < mealCount - 1; i++) {
            int min = i;

            for (int j = i + 1; j < mealCount; j++) {

                if (meals[j].getName()
                        .compareToIgnoreCase(meals[min].getName()) < 0) {

                    min = j;
                }
            }

            swap(i, min);
        }
    }

    public void sortByNutritionalInfo() {

        for (int i = 0; i < mealCount - 1; i++) {
            int min = i;

            for (int j = i + 1; j < mealCount; j++) {

                if (meals[j].getNutritionalInfo()
                        .compareToIgnoreCase(meals[min].getNutritionalInfo()) < 0) {

                    min = j;
                }
            }

            swap(i, min);
        }
    }

    public Meal searchByName(String name) {

        sortByName();

        int low = 0;
        int high = mealCount - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result = meals[mid].getName()
                    .compareToIgnoreCase(name);

            if (result == 0) {
                return meals[mid];
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    private void swap(int a, int b) {

        Meal temp = meals[a];
        meals[a] = meals[b];
        meals[b] = temp;
    }
}