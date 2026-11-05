/**
 * WorkoutPanel
 * Author: Alexa Olaes
 * Description: UI panel for logging and displaying workouts.
 *              Lets the user enter workout name, duration, and calories burned,
 *              and shows the list of saved workouts.
 */
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
        
        btnadd.addActionListener(this);
        btndelete.addActionListener(this);
        
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnadd) {
           //FUNCTION 
        } else if (e.getSource() == btndelete) {
            //FUNCTION
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


