package com.mycompany.fitnesstrackerapp.ui;

import com.mycompany.fitnesstrackerapp.model.User;
import com.mycompany.fitnesstrackerapp.profile.UserProfile;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SettingsPanel extends JPanel {

    private final User user;
    private final Runnable onLogout;

    private JLabel lblTitle, lblProfile, lblName, lblUsername;
    private JLabel lblPassword, lblCurrent, lblNew, lblConfirm;
    private JTextField txtName, txtUsername;
    private JPasswordField txtCurrentPassword, txtNewPassword, txtConfirmPassword;
    private JButton btnSaveProfile, btnChangePassword, btnLogout;

    public SettingsPanel(User user) {
        this(user, null);
    }

    public SettingsPanel(User user, Runnable onLogout) {
        this.user = user;
        this.onLogout = onLogout;
        setLayout(null);
        setPreferredSize(new Dimension(620, 450));
        initComponents();
        loadUserData();
    }

    private void initComponents() {
        lblTitle = new JLabel("Account Settings", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(0, 10, 620, 30);
        add(lblTitle);

        // ---------- Profile ----------
        lblProfile = new JLabel("Profile");
        lblProfile.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblProfile.setBounds(40, 55, 200, 25);
        add(lblProfile);

        lblName = new JLabel("Name:");
        lblName.setBounds(40, 90, 200, 25);
        add(lblName);
        txtName = new JTextField();
        txtName.setBounds(250, 90, 280, 25);
        add(txtName);

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(40, 125, 200, 25);
        add(lblUsername);
        txtUsername = new JTextField();
        txtUsername.setBounds(250, 125, 280, 25);
        add(txtUsername);

        btnSaveProfile = new JButton("Save Profile");
        btnSaveProfile.setBounds(250, 160, 140, 30);
        btnSaveProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        add(btnSaveProfile);

        // ---------- Change Password ----------
        lblPassword = new JLabel("Change Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPassword.setBounds(40, 215, 200, 25);
        add(lblPassword);

        lblCurrent = new JLabel("Current Password:");
        lblCurrent.setBounds(40, 250, 200, 25);
        add(lblCurrent);
        txtCurrentPassword = new JPasswordField();
        txtCurrentPassword.setBounds(250, 250, 280, 25);
        add(txtCurrentPassword);

        lblNew = new JLabel("New Password:");
        lblNew.setBounds(40, 285, 200, 25);
        add(lblNew);
        txtNewPassword = new JPasswordField();
        txtNewPassword.setBounds(250, 285, 280, 25);
        add(txtNewPassword);

        lblConfirm = new JLabel("Confirm New Password:");
        lblConfirm.setBounds(40, 320, 200, 25);
        add(lblConfirm);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(250, 320, 280, 25);
        add(txtConfirmPassword);

        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setBounds(250, 355, 160, 30);
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
        add(btnChangePassword);

        // ---------- Logout ----------
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(460, 405, 120, 30);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onLogout != null) {
                    onLogout.run();
                }
            }
        });
        add(btnLogout);
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