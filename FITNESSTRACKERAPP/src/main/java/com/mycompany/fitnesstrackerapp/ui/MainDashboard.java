package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainDashboard extends JFrame implements ActionListener {

    JLabel lblTitle;
    JButton btnHome, btnWorkout, btnMeals;
    JButton btnProgress, btnGoals, btnSettings, btnLogout;
    JPanel pnlWorkouts, pnlCalories;
    JPanel pnlGoal, pnlRecentActivity;
    JLabel lblWorkouts, lblCalories;
    JLabel lblGoal, lblRecentActivity;

    public MainDashboard() {

        setLayout(null);
        setSize(1000, 700);
        setTitle("Main Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("FITNESS TRACKER DASHBOARD");
        lblTitle.setBounds(300, 40, 400, 50);
        add(lblTitle);

        btnHome = new JButton("HOME");
        btnHome.setBounds(50, 130, 150, 50);
        add(btnHome);

        btnWorkout = new JButton("WORKOUTS");
        btnWorkout.setBounds(50, 200, 150, 50);
        add(btnWorkout);

        btnMeals = new JButton("MEALS");
        btnMeals.setBounds(50, 270, 150, 50);
        add(btnMeals);

        btnProgress = new JButton("PROGRESS");
        btnProgress.setBounds(50, 340, 150, 50);
        add(btnProgress);

        btnGoals = new JButton("GOALS");
        btnGoals.setBounds(50, 410, 150, 50);
        add(btnGoals);

        btnSettings = new JButton("SETTINGS");
        btnSettings.setBounds(50, 480, 150, 50);
        add(btnSettings);

        btnLogout = new JButton("LOGOUT");
        btnLogout.setBounds(50, 550, 150, 50);
        add(btnLogout);

        pnlWorkouts = new JPanel();
        pnlWorkouts.setLayout(null);
        pnlWorkouts.setBorder(BorderFactory.createTitledBorder("Total Workouts"));
        pnlWorkouts.setBounds(250, 130, 200, 130);
        add(pnlWorkouts);

        lblWorkouts = new JLabel("");
        lblWorkouts.setBounds(20, 40, 150, 50);
        pnlWorkouts.add(lblWorkouts);

        pnlCalories = new JPanel();
        pnlCalories.setLayout(null);
        pnlCalories.setBorder(BorderFactory.createTitledBorder("Total Calories"));
        pnlCalories.setBounds(480, 130, 200, 130);
        add(pnlCalories);

        lblCalories = new JLabel("");
        lblCalories.setBounds(20, 40, 150, 50);
        pnlCalories.add(lblCalories);

        pnlGoal = new JPanel();
        pnlGoal.setLayout(null);
        pnlGoal.setBorder(BorderFactory.createTitledBorder("Fitness Goal"));
        pnlGoal.setBounds(710, 130, 200, 130);
        add(pnlGoal);

        lblGoal = new JLabel("");
        lblGoal.setBounds(20, 40, 150, 50);
        pnlGoal.add(lblGoal);

        pnlRecentActivity = new JPanel();
        pnlRecentActivity.setLayout(null);
        pnlRecentActivity.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        pnlRecentActivity.setBounds(250, 300, 660, 250);
        add(pnlRecentActivity);

        lblRecentActivity = new JLabel("");
        lblRecentActivity.setBounds(20, 40, 600, 150);
        pnlRecentActivity.add(lblRecentActivity);

        btnHome.addActionListener(this);
        btnWorkout.addActionListener(this);
        btnMeals.addActionListener(this);
        btnProgress.addActionListener(this);
        btnGoals.addActionListener(this);
        btnSettings.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    // Ang WorkoutPanels, SettingsPanel at GoalsPanel ay JPanel (hindi JFrame),
    // kaya kailangan natin silang ilagay sa JFrame para makita.
    private void openPanel(String title, JPanel panel) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnWorkout) {
            openPanel("Workouts", new WorkoutPanels());
        }

        if (e.getSource() == btnMeals) {
            MealPanel app = new MealPanel();
            app.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btnProgress) {
            ProgressPanel app = new ProgressPanel();
            app.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btnGoals) {
            openPanel("Goals", new GoalsPanel());
        }

        if (e.getSource() == btnSettings) {
            openPanel("Settings", new SettingsPanel());
        }

        if (e.getSource() == btnLogout) {
            LoginFormUI app = new LoginFormUI();
            app.setVisible(true);
            this.dispose();
        }
    }

   
}