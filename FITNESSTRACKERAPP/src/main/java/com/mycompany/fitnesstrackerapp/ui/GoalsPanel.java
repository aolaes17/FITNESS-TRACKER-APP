package com.mycompany.fitnesstrackerapp.ui;

import com.mycompany.fitnesstrackerapp.model.Meal;
import com.mycompany.fitnesstrackerapp.model.User;
import com.mycompany.fitnesstrackerapp.model.Workout;
import com.mycompany.fitnesstrackerapp.profile.UserProfile;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class GoalsPanel extends JPanel {

    private final User user;
    private UserProfile.Goals goals = new UserProfile.Goals(0, 0, 0);

    private JLabel lblTitle, lblIntake, lblBurn, lblMinutes, lblProgress;
    private JLabel lblBarIntake, lblBarBurn, lblBarMinutes;
    private JTextField txtCalorieIntake, txtCalorieBurn, txtWeeklyMinutes;
    private JButton btnSaveGoals;
    private JProgressBar barIntake, barBurn, barMinutes;

    public GoalsPanel(User user) {
        this.user = user;
        setLayout(null);
        setPreferredSize(new Dimension(620, 450));
        initComponents();
        loadGoals();
        refreshProgress();
    }

    private void initComponents() {
        lblTitle = new JLabel("My Goals", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(0, 10, 620, 30);
        add(lblTitle);

        // ---------- Set Goals ----------
        lblIntake = new JLabel("Daily Calorie Intake Goal (kcal):");
        lblIntake.setBounds(40, 60, 280, 25);
        add(lblIntake);
        txtCalorieIntake = new JTextField();
        txtCalorieIntake.setBounds(330, 60, 200, 25);
        add(txtCalorieIntake);

        lblBurn = new JLabel("Daily Calories Burned Goal (kcal):");
        lblBurn.setBounds(40, 95, 280, 25);
        add(lblBurn);
        txtCalorieBurn = new JTextField();
        txtCalorieBurn.setBounds(330, 95, 200, 25);
        add(txtCalorieBurn);

        lblMinutes = new JLabel("Weekly Workout Minutes Goal:");
        lblMinutes.setBounds(40, 130, 280, 25);
        add(lblMinutes);
        txtWeeklyMinutes = new JTextField();
        txtWeeklyMinutes.setBounds(330, 130, 200, 25);
        add(txtWeeklyMinutes);

        btnSaveGoals = new JButton("Save Goals");
        btnSaveGoals.setBounds(250, 170, 120, 30);
        btnSaveGoals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGoals();
            }
        });
        add(btnSaveGoals);

        // ---------- Progress ----------
        lblProgress = new JLabel("Progress");
        lblProgress.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblProgress.setBounds(40, 220, 300, 25);
        add(lblProgress);

        lblBarIntake = new JLabel("Calories Consumed (Today)");
        lblBarIntake.setBounds(40, 255, 400, 20);
        add(lblBarIntake);
        barIntake = createBar();
        barIntake.setBounds(40, 278, 540, 25);
        add(barIntake);

        lblBarBurn = new JLabel("Calories Burned (Today)");
        lblBarBurn.setBounds(40, 315, 400, 20);
        add(lblBarBurn);
        barBurn = createBar();
        barBurn.setBounds(40, 338, 540, 25);
        add(barBurn);

        lblBarMinutes = new JLabel("Workout Minutes (This Week)");
        lblBarMinutes.setBounds(40, 375, 400, 20);
        add(lblBarMinutes);
        barMinutes = createBar();
        barMinutes.setBounds(40, 398, 540, 25);
        add(barMinutes);
    }

    private JProgressBar createBar() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        return bar;
    }

    private void loadGoals() {
        try {
            goals = UserProfile.getGoals(user.getId());
            txtCalorieIntake.setText(String.valueOf(goals.getDailyCalorieIntake()));
            txtCalorieBurn.setText(String.valueOf(goals.getDailyCalorieBurn()));
            txtWeeklyMinutes.setText(String.valueOf(goals.getWeeklyWorkoutMinutes()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not load goals: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveGoals() {
        try {
            double intake = Double.parseDouble(txtCalorieIntake.getText().trim());
            double burn = Double.parseDouble(txtCalorieBurn.getText().trim());
            int minutes = Integer.parseInt(txtWeeklyMinutes.getText().trim());

            if (intake < 0 || burn < 0 || minutes < 0) {
                JOptionPane.showMessageDialog(this, "Goals cannot be negative.",
                        "Invalid input", JOptionPane.WARNING_MESSAGE);
                return;
            }
            UserProfile.Goals newGoals = new UserProfile.Goals(intake, burn, minutes);
            UserProfile.saveGoals(user.getId(), newGoals);
            goals = newGoals;
            refreshProgress();
            JOptionPane.showMessageDialog(this, "Goals saved.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers (minutes must be a whole number).",
                    "Invalid input", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshProgress() {
        LocalDate today = LocalDate.now();
        // Lunes ng linggong ito (Monday = 1, kaya 0 ang aalisin kapag Lunes ngayon)
        LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - 1);

        double consumedToday = 0;
        for (Meal m : user.getMeals()) {
            if (m.getLoggedAt() != null && m.getLoggedAt().toLocalDate().equals(today)) {
                consumedToday += m.getCalories();
            }
        }

        double burnedToday = 0;
        int minutesThisWeek = 0;
        for (Workout w : user.getWorkouts()) {
            if (w.getLoggedAt() == null) {
                continue;
            }
            LocalDate date = w.getLoggedAt().toLocalDate();

            if (date.equals(today)) {
                burnedToday += w.getCaloriesBurned();
            }
            if (!date.isBefore(weekStart) && !date.isAfter(today)) {
                minutesThisWeek += w.getDuration();
            }
        }

        setBar(barIntake, consumedToday, goals.getDailyCalorieIntake(), "kcal");
        setBar(barBurn, burnedToday, goals.getDailyCalorieBurn(), "kcal");
        setBar(barMinutes, minutesThisWeek, goals.getWeeklyWorkoutMinutes(), "min");
    }

    private void setBar(JProgressBar bar, double value, double goal, String unit) {
        if (goal <= 0) {
            bar.setValue(0);
            bar.setString("No goal set");
            return;
        }
        int pct = (int) Math.min(100, Math.round(value / goal * 100));
        bar.setValue(pct);
        bar.setString(String.format("%.0f / %.0f %s (%d%%)", value, goal, unit, pct));
    }
}