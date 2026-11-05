package com.mycompany.fitnesstrackerapp.ui;

import com.mycompany.fitnesstrackerapp.meal.MealManager;
import com.mycompany.fitnesstrackerapp.model.Meal;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MealPanel extends JFrame implements ActionListener {

    JLabel lblTitle;
    JLabel lblMealName, lblCalories, lblNutritionalInfo;
    JLabel lblSearch, lblSort;
    JTextField txtMealName, txtCalories, txtNutritionalInfo;
    JTextField txtSearch;
    JComboBox<String> cmbSort;
    JButton btnAdd, btnClear, btnSearch, btnSort, btnBack;
    JTextArea txtMeals;

    MealManager mealManager = new MealManager();
    int currentUserId = 1; 

    public MealPanel() {
        setLayout(null);
        setSize(1000, 700);
        setTitle("Meals");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("MEALS TRACKER");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBounds(300, 20, 400, 40);
        add(lblTitle);

        lblMealName = new JLabel("Meal Name:");
        lblMealName.setBounds(50, 80, 120, 25);
        add(lblMealName);

        txtMealName = new JTextField();
        txtMealName.setBounds(180, 80, 250, 25);
        add(txtMealName);

        lblCalories = new JLabel("Calories:");
        lblCalories.setBounds(50, 120, 120, 25);
        add(lblCalories);

        txtCalories = new JTextField();
        txtCalories.setBounds(180, 120, 250, 25);
        add(txtCalories);

        lblNutritionalInfo = new JLabel("Nutritional Info:");
        lblNutritionalInfo.setBounds(50, 160, 120, 25);
        add(lblNutritionalInfo);

        txtNutritionalInfo = new JTextField();
        txtNutritionalInfo.setBounds(180, 160, 250, 25);
        add(txtNutritionalInfo);

        btnAdd = new JButton("Add Meal");
        btnAdd.setBounds(50, 210, 180, 35);
        add(btnAdd);

        btnClear = new JButton("Clear");
        btnClear.setBounds(250, 210, 180, 35);
        add(btnClear);

        lblSearch = new JLabel("Search Meal Name:");
        lblSearch.setBounds(550, 80, 200, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(550, 110, 250, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(820, 110, 100, 25);
        add(btnSearch);

        lblSort = new JLabel("Sort Meals By:");
        lblSort.setBounds(550, 160, 200, 25);
        add(lblSort);

        String[] sortOptions = {"Calories", "Name", "Nutritional Info"};
        cmbSort = new JComboBox<String>(sortOptions);
        cmbSort.setBounds(550, 190, 250, 25);
        add(cmbSort);

        btnSort = new JButton("Sort");
        btnSort.setBounds(820, 190, 100, 25);
        add(btnSort);

        txtMeals = new JTextArea();
        txtMeals.setEditable(false);
        txtMeals.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(txtMeals);
        scrollPane.setBounds(50, 270, 870, 330);
        add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(50, 610, 150, 35);
        add(btnBack);

        
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnSort.addActionListener(this);
        btnBack.addActionListener(this);

        
        mealManager.loadMeals(currentUserId);
        txtMeals.setText(mealManager.getMealsAsText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            MainDashboard app = new MainDashboard();
            app.setVisible(true);
            this.dispose();
        }
 
        if (e.getSource() == btnClear) {
            txtMealName.setText("");
            txtCalories.setText("");
            txtNutritionalInfo.setText("");
            txtSearch.setText("");
            mealManager.loadMeals(currentUserId);
            txtMeals.setText(mealManager.getMealsAsText());
        }
 
        if (e.getSource() == btnAdd) {
            String name = txtMealName.getText().trim();
            String calStr = txtCalories.getText().trim();
            String nutrition = txtNutritionalInfo.getText().trim();

            if (name.isEmpty() || calStr.isEmpty() || nutrition.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            } else {
                try {
                    double calories = Double.parseDouble(calStr);
                    boolean success = mealManager.saveMeal(currentUserId, name, calories, nutrition);
                    
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Meal added successfully!");
                        mealManager.loadMeals(currentUserId);
                        txtMeals.setText(mealManager.getMealsAsText());
                        
                        txtMealName.setText("");
                        txtCalories.setText("");
                        txtNutritionalInfo.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to save meal.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Calories must be a valid number!");
                }
            }
        }

        if (e.getSource() == btnSearch) {
            String searchName = txtSearch.getText().trim();
            if (searchName.isEmpty()) {
                mealManager.loadMeals(currentUserId);
                txtMeals.setText(mealManager.getMealsAsText());
            } else {
                Meal found = mealManager.searchByName(searchName);
                if (found != null) {
                    String resultText = "Found:\nID: " + found.getMealId()
                            + " | " + found.getName()
                            + " | " + found.getCalories() + " cal"
                            + " | " + found.getNutritionalInfo() + "\n";
                    txtMeals.setText(resultText);
                } else {
                    txtMeals.setText("Meal not found.");
                }
            }
        }

        if (e.getSource() == btnSort) {
            String selectedOption = (String) cmbSort.getSelectedItem();
            
            if ("Calories".equals(selectedOption)) {
                mealManager.sortByCalories();
            } else if ("Name".equals(selectedOption)) {
                mealManager.sortByName();
            } else if ("Nutritional Info".equals(selectedOption)) {
                mealManager.sortByNutritionalInfo();
            }
            
            txtMeals.setText(mealManager.getMealsAsText());
        }
    }
}
