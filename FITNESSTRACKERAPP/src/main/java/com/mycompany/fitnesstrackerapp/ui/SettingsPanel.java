/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnesstrackerapp.ui;

import com.mycompany.fitnesstrackerapp.model.User;
import com.mycompany.fitnesstrackerapp.profile.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SettingsPanel extends JPanel {

    private final User user;
    private final Runnable onLogout;   // ibibigay ni Noel mula sa MainDashboard (pwedeng null)

    private JTextField txtName, txtUsername;
    private JPasswordField txtCurrentPassword, txtNewPassword, txtConfirmPassword;
    private JButton btnSaveProfile, btnChangePassword, btnLogout;

    public SettingsPanel(User user) {
        this(user, null);
    }

    public SettingsPanel(User user, Runnable onLogout) {
        this.user = user;
        this.onLogout = onLogout;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
        loadUserData();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Account Settings", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(createProfileSection());
        content.add(Box.createVerticalStrut(15));
        content.add(createPasswordSection());
        add(content, BorderLayout.CENTER);

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            if (onLogout != null) {
                onLogout.run();
            }
        });
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnLogout);
        add(bottom, BorderLayout.SOUTH);
    }

    private JPanel createProfileSection() {
        txtName = new JTextField();
        txtUsername = new JTextField();

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.add(new JLabel("Name:"));
        form.add(txtName);
        form.add(new JLabel("Username:"));
        form.add(txtUsername);

        btnSaveProfile = new JButton("Save Profile");
        btnSaveProfile.addActionListener(e -> saveProfile());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(btnSaveProfile);

        JPanel section = new JPanel(new BorderLayout(10, 10));
        section.setBorder(BorderFactory.createTitledBorder("Profile"));
        section.add(form, BorderLayout.CENTER);
        section.add(buttons, BorderLayout.SOUTH);
        return section;
    }

    private JPanel createPasswordSection() {
        txtCurrentPassword = new JPasswordField();
        txtNewPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.add(new JLabel("Current Password:"));
        form.add(txtCurrentPassword);
        form.add(new JLabel("New Password:"));
        form.add(txtNewPassword);
        form.add(new JLabel("Confirm New Password:"));
        form.add(txtConfirmPassword);

        btnChangePassword = new JButton("Change Password");
        btnChangePassword.addActionListener(e -> changePassword());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(btnChangePassword);

        JPanel section = new JPanel(new BorderLayout(10, 10));
        section.setBorder(BorderFactory.createTitledBorder("Change Password"));
        section.add(form, BorderLayout.CENTER);
        section.add(buttons, BorderLayout.SOUTH);
        return section;
    }

    private void loadUserData() {
        txtName.setText(user.getName());
        txtUsername.setText(user.getUsername());
    }

    private void saveProfile() {
        try {
            UserProfile.updateProfile(user, txtName.getText(), txtUsername.getText());
            JOptionPane.showMessageDialog(this, "Profile updated.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changePassword() {
        String current = new String(txtCurrentPassword.getPassword());
        String newPass = new String(txtNewPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());

        if (!newPass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match.",
                    "Invalid input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            UserProfile.changePassword(user, current, newPass);
            txtCurrentPassword.setText("");
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");
            JOptionPane.showMessageDialog(this, "Password changed.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

