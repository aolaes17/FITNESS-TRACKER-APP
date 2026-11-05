package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GoalsPanel extends JPanel implements ActionListener {

    private JLabel hdrGoals, lblIntake, lblBurn, lblMinutes, lblProgress;
    private JLabel lblBarIntake, lblBarBurn, lblBarMinutes;
    private JTextField txtIntake, txtBurn, txtMinutes;
    private JButton btnSave;
    private JProgressBar barIntake, barBurn, barMinutes;

    public GoalsPanel() {
        setSize(700, 480);
        setLayout(null);

        hdrGoals = new JLabel("GOALS", SwingConstants.CENTER);
        hdrGoals.setBounds(250, 20, 200, 30);
        add(hdrGoals);

        // ---------- Set Goals ----------
        lblIntake = new JLabel("Daily Calorie Intake (kcal): ");
        lblIntake.setBounds(60, 70, 230, 20);
        add(lblIntake);

        txtIntake = new JTextField();
        txtIntake.setBounds(300, 70, 150, 25);
        add(txtIntake);

        lblBurn = new JLabel("Daily Calories Burned (kcal): ");
        lblBurn.setBounds(60, 105, 230, 20);
        add(lblBurn);

        txtBurn = new JTextField();
        txtBurn.setBounds(300, 105, 150, 25);
        add(txtBurn);

        lblMinutes = new JLabel("Weekly Workout Minutes: ");
        lblMinutes.setBounds(60, 140, 230, 20);
        add(lblMinutes);

        txtMinutes = new JTextField();
        txtMinutes.setBounds(300, 140, 150, 25);
        add(txtMinutes);

        btnSave = new JButton("Save Goals");
        btnSave.setBounds(300, 180, 110, 25);
        add(btnSave);

        btnSave.addActionListener(this);

        // ---------- Progress ----------
        lblProgress = new JLabel("Progress");
        lblProgress.setBounds(60, 230, 200, 20);
        add(lblProgress);

        lblBarIntake = new JLabel("Calories Consumed (Today)");
        lblBarIntake.setBounds(60, 260, 250, 20);
        add(lblBarIntake);

        barIntake = new JProgressBar(0, 100);
        barIntake.setStringPainted(true);
        barIntake.setBounds(60, 282, 580, 22);
        add(barIntake);

        lblBarBurn = new JLabel("Calories Burned (Today)");
        lblBarBurn.setBounds(60, 315, 250, 20);
        add(lblBarBurn);

        barBurn = new JProgressBar(0, 100);
        barBurn.setStringPainted(true);
        barBurn.setBounds(60, 337, 580, 22);
        add(barBurn);

        lblBarMinutes = new JLabel("Workout Minutes (This Week)");
        lblBarMinutes.setBounds(60, 370, 250, 20);
        add(lblBarMinutes);

        barMinutes = new JProgressBar(0, 100);
        barMinutes.setStringPainted(true);
        barMinutes.setBounds(60, 392, 580, 22);
        add(barMinutes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            //FUNCTION
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Goals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GoalsPanel());
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}