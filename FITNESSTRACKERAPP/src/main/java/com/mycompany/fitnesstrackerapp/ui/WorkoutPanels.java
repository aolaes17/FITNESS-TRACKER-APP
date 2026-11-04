
package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WorkoutPanels extends JPanel implements ActionListener {
    
    private JLabel hdrworkout, lblname, lblcalories, lblDuration;
    private JTextField txtname, txtDuration, txtCalories; 
   private JButton btnadd, btndelete;
    private JTable tblWorkouts;
    private JScrollPane scrollWorkouts;
    private DefaultTableModel model;
    private static final String columns[] = {"ID", "Name", "Duration (min)", "Calories Burned"};
    
    WorkoutPanels(){
        setSize(700,480);
        setLayout(null);
        
        hdrworkout = new JLabel("WORKOUT");
        hdrworkout.setBounds(350,20,200,30);
        add(hdrworkout);    
        
        lblname = new JLabel("Name: ");
        lblname.setBounds(30, 70, 60, 20);
        add(lblname);
        
        txtname = new JTextField();
        txtname.setBounds(90, 70, 120,25);
        add(txtname);
        
        lblDuration = new JLabel("Minutes: ");
        lblDuration.setBounds(230, 70, 75, 20);
        add(lblDuration);
        
        txtDuration = new JTextField();
        txtDuration.setBounds(305,70,120,25);
        add(txtDuration);
        
        lblcalories = new JLabel("Calories: ");
        lblcalories.setBounds(435,70,75,20);
        add(lblcalories);
        
        txtCalories = new JTextField();
        txtCalories.setBounds(510,70,120,25);
        add(txtCalories);
        
        btnadd = new JButton("Add");
        btnadd.setBounds(220,110,85,20);
        add(btnadd);
        
        btndelete = new JButton("Delete");
        btndelete.setBounds(400,110,85,20);
        add(btndelete);
        
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblWorkouts = new JTable(model);
        tblWorkouts.setRowHeight(24);
        tblWorkouts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollWorkouts = new JScrollPane(tblWorkouts);
        scrollWorkouts.setBounds(30, 160, 640, 280);
        add(scrollWorkouts);

        //Sample data (pang-display lang)
        //model.addRow(new Object[]{1, "Running", 30, 300.0});
        //model.addRow(new Object[]{2, "Push-ups", 15, 100.0});
        //model.addRow(new Object[]{3, "Cycling", 45, 400.0});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnadd) {
            // TODO
        } else if (e.getSource() == btndelete) {
            // TODO
        }
        
    }
     public static void main(String[] args) {
        JFrame frame = new JFrame("Workouts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new WorkoutPanels());
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


