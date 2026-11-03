package com.mycompany.fitnesstrackerapp.ui;

import com.mycompany.fitnesstrackerapp.model.Meal;
import com.mycompany.fitnesstrackerapp.model.User;
import com.mycompany.fitnesstrackerapp.model.Workout;
import com.mycompany.fitnesstrackerapp.profile.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GoalsPanel extends JPanel {

    private final User user;
    private UserProfile.Goals goals = new UserProfile.Goals(0, 0, 0);

    private JTextField txtCalorieIntake, txtCalorieBurn, txtWeeklyMinutes;
    private JButton btnSaveGoals;
    private JProgressBar barIntake, barBurn, barMinutes;

    public GoalsPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
        loadGoals();
        refreshProgress();
    }

    
    private void initComponents() {
        JLabel lblTitle = new JLabel("My Goals", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        // Form
        txtCalorieIntake = new JTextField();
        txtCalorieBurn = new JTextField();
        txtWeeklyMinutes = new JTextField();

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.add(new JLabel("Daily Calorie Intake Goal (kcal):"));
        form.add(txtCalorieIntake);
        form.add(new JLabel("Daily Calories Burned Goal (kcal):"));
        form.add(txtCalorieBurn);
        form.add(new JLabel("Weekly Workout Minutes Goal:"));
        form.add(txtWeeklyMinutes);

        btnSaveGoals = new JButton("Save Goals");
        btnSaveGoals.addActionListener(e -> saveGoals());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(btnSaveGoals);

        JPanel formSection = new JPanel(new BorderLayout(10, 10));
        formSection.setBorder(BorderFactory.createTitledBorder("Set Your Goals"));
        formSection.add(form, BorderLayout.CENTER);
        formSection.add(buttons, BorderLayout.SOUTH);

        // Progress
        barIntake = createBar();
        barBurn = createBar();
        barMinutes = createBar();

        JPanel progress = new JPanel(new GridLayout(3, 1, 10, 10));
        progress.setBorder(BorderFactory.createTitledBorder("Progress"));
        progress.add(wrapBar("Calories Consumed", barIntake));
        progress.add(wrapBar("Calories Burned", barBurn));
        progress.add(wrapBar("Workout Minutes", barMinutes));

        JPanel center = new JPanel(new BorderLayout(10, 15));
        center.add(formSection, BorderLayout.NORTH);
        center.add(progress, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
    }

    private JProgressBar createBar() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        return bar;
    }

    private JPanel wrapBar(String label, JProgressBar bar) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(bar, BorderLayout.CENTER);
        return p;
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
        double consumed = 0;
        for (Meal m : user.getMeals()) {
            consumed += m.getCalories();
        }
        double burned = 0;
        int minutes = 0;
        for (Workout w : user.getWorkouts()) {
            burned += w.getCaloriesBurned();
            minutes += w.getDuration();
        }
        setBar(barIntake, consumed, goals.getDailyCalorieIntake(), "kcal");
        setBar(barBurn, burned, goals.getDailyCalorieBurn(), "kcal");
        setBar(barMinutes, minutes, goals.getWeeklyWorkoutMinutes(), "min");
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