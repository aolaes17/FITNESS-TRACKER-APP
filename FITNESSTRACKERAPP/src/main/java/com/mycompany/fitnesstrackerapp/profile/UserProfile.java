/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnesstrackerapp.profile;

import com.mycompany.fitnesstrackerapp.auth.AuthService;
import com.mycompany.fitnesstrackerapp.db.DatabaseConnection;
import com.mycompany.fitnesstrackerapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile {

    public static class Goals {
        private final double dailyCalorieIntake;
        private final double dailyCalorieBurn;
        private final int weeklyWorkoutMinutes;

        public Goals(double dailyCalorieIntake, double dailyCalorieBurn, int weeklyWorkoutMinutes) {
            this.dailyCalorieIntake = dailyCalorieIntake;
            this.dailyCalorieBurn = dailyCalorieBurn;
            this.weeklyWorkoutMinutes = weeklyWorkoutMinutes;
        }

        public double getDailyCalorieIntake() { return dailyCalorieIntake; }
        public double getDailyCalorieBurn() { return dailyCalorieBurn; }
        public int getWeeklyWorkoutMinutes() { return weeklyWorkoutMinutes; }
    }

    // ---------- PROFILE ----------

    public static void updateProfile(User user, String newName, String newUsername) throws SQLException {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (newUsername == null || newUsername.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters.");
        }
        String name = newName.trim();
        String username = newUsername.trim();

        try (Connection con = DatabaseConnection.getConnection()) {
            try (PreparedStatement check = con.prepareStatement(
                    "SELECT id FROM users WHERE username = ? AND id <> ?")) {
                check.setString(1, username);
                check.setInt(2, user.getId());
                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        throw new IllegalArgumentException("Username is already taken.");
                    }
                }
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET name = ?, username = ? WHERE id = ?")) {
                ps.setString(1, name);
                ps.setString(2, username);
                ps.setInt(3, user.getId());
                ps.executeUpdate();
            }
        }
        user.setName(name);
        user.setUsername(username);
    }

    /** Pinapalitan ang password matapos i-verify ang kasalukuyan. */
    public static void changePassword(User user, String currentPassword, String newPassword) throws SQLException {
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("New password must be at least 6 characters.");
        }
        try (Connection con = DatabaseConnection.getConnection()) {
            try (PreparedStatement check = con.prepareStatement(
                    "SELECT id FROM users WHERE id = ? AND password = ?")) {
                check.setInt(1, user.getId());
                check.setString(2, AuthService.hashPassword(currentPassword == null ? "" : currentPassword));
                try (ResultSet rs = check.executeQuery()) {
                    if (!rs.next()) {
                        throw new IllegalArgumentException("Current password is incorrect.");
                    }
                }
            }
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET password = ? WHERE id = ?")) {
                ps.setString(1, AuthService.hashPassword(newPassword));
                ps.setInt(2, user.getId());
                ps.executeUpdate();
            }
        }
    }

    // ---------- GOALS ----------

    /** Kung wala pang naka-save, 0 ang lahat. */
    public static Goals getGoals(int userId) throws SQLException {
        String sql = "SELECT daily_calorie_intake, daily_calorie_burn, weekly_workout_minutes "
                   + "FROM goals WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Goals(rs.getDouble("daily_calorie_intake"),
                            rs.getDouble("daily_calorie_burn"),
                            rs.getInt("weekly_workout_minutes"));
                }
            }
        }
        return new Goals(0, 0, 0);
    }

    public static void saveGoals(int userId, Goals goals) throws SQLException {
        String sql = "INSERT INTO goals (user_id, daily_calorie_intake, daily_calorie_burn, weekly_workout_minutes) "
                   + "VALUES (?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE daily_calorie_intake = VALUES(daily_calorie_intake), "
                   + "daily_calorie_burn = VALUES(daily_calorie_burn), "
                   + "weekly_workout_minutes = VALUES(weekly_workout_minutes)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDouble(2, goals.getDailyCalorieIntake());
            ps.setDouble(3, goals.getDailyCalorieBurn());
            ps.setInt(4, goals.getWeeklyWorkoutMinutes());
            ps.executeUpdate();
        }
    }
}
