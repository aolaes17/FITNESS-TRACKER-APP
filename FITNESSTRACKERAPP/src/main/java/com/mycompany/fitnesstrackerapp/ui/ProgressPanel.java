package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProgressPanel extends JFrame implements ActionListener{
    
     JLabel lblTitle, lblTotalWorkouts;
    JLabel lblCalories, lblAverage;
    JLabel lblHistory;

    JComboBox<String> cbTimeRange;

    JTextArea txtHistory;

    JButton btnBack;

    public ProgressPanel() {

        setLayout(null);
        setSize(1000, 700);
        setTitle("Progress Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("YOUR FITNESS PROGRESS");
        lblTitle.setBounds(350, 50, 350, 50);
        add(lblTitle);

        cbTimeRange = new JComboBox<String>(
            new String[]{"Select Time Range",
                         "Last 1 Week",
                         "Last 1 Month",
                         "Last 3 Months",
                         "Last 1 Year"}
        );

        cbTimeRange.setBounds(100, 130, 200, 40);
        add(cbTimeRange);

        lblTotalWorkouts = new JLabel("Total Workouts:");
        lblTotalWorkouts.setBounds(100, 210, 250, 40);
        add(lblTotalWorkouts);

        lblCalories = new JLabel("Total Calories:");
        lblCalories.setBounds(100, 270, 250, 40);
        add(lblCalories);

        lblAverage = new JLabel("Average per Week:");
        lblAverage.setBounds(100, 330, 250, 40);
        add(lblAverage);

        lblHistory = new JLabel("WORKOUT HISTORY");
        lblHistory.setBounds(450, 150, 250, 40);
        add(lblHistory);

        txtHistory = new JTextArea();
        txtHistory.setBounds(400, 200, 450, 200);
        txtHistory.setEditable(false);
        add(txtHistory);

        btnBack = new JButton("BACK");
        btnBack.setBounds(100, 500, 150, 50);
        add(btnBack);

        btnBack.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack){
            MainDashboard app = new MainDashboard();
        app.setVisible(true);

        this.dispose();
        }
    }
    
}
