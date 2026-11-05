package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsPanel extends JPanel implements ActionListener {

    private JLabel hdrSettings, lblProfile, lblName, lblUsername;
    private JLabel lblPassword, lblCurrent, lblNew, lblConfirm;
    private JTextField txtName, txtUsername;
    private JPasswordField txtCurrent, txtNew, txtConfirm;
    private JButton btnSaveProfile, btnChangePassword, btnLogout;

    public SettingsPanel() {
        setSize(700, 480);
        setLayout(null);

        hdrSettings = new JLabel("SETTINGS", SwingConstants.CENTER);
        hdrSettings.setBounds(250, 20, 200, 30);
        add(hdrSettings);

        // ---------- Profile ----------
        lblProfile = new JLabel("Profile");
        lblProfile.setBounds(60, 65, 200, 20);
        add(lblProfile);

        lblName = new JLabel("Name: ");
        lblName.setBounds(60, 95, 150, 20);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(220, 95, 250, 25);
        add(txtName);

        lblUsername = new JLabel("Username: ");
        lblUsername.setBounds(60, 130, 150, 20);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(220, 130, 250, 25);
        add(txtUsername);

        btnSaveProfile = new JButton("Save Profile");
        btnSaveProfile.setBounds(220, 165, 120, 25);
        add(btnSaveProfile);

        // ---------- Change Password ----------
        lblPassword = new JLabel("Change Password");
        lblPassword.setBounds(60, 215, 200, 20);
        add(lblPassword);

        lblCurrent = new JLabel("Current Password: ");
        lblCurrent.setBounds(60, 245, 150, 20);
        add(lblCurrent);

        txtCurrent = new JPasswordField();
        txtCurrent.setBounds(220, 245, 250, 25);
        add(txtCurrent);

        lblNew = new JLabel("New Password: ");
        lblNew.setBounds(60, 280, 150, 20);
        add(lblNew);

        txtNew = new JPasswordField();
        txtNew.setBounds(220, 280, 250, 25);
        add(txtNew);

        lblConfirm = new JLabel("Confirm Password: ");
        lblConfirm.setBounds(60, 315, 150, 20);
        add(lblConfirm);

        txtConfirm = new JPasswordField();
        txtConfirm.setBounds(220, 315, 250, 25);
        add(txtConfirm);

        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setBounds(220, 350, 150, 25);
        add(btnChangePassword);

        // ---------- Logout ----------
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(540, 410, 100, 25);
        add(btnLogout);

        btnSaveProfile.addActionListener(this);
        btnChangePassword.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSaveProfile) {
            //FUNCTION
        } else if (e.getSource() == btnChangePassword) {
            //FUNCTION
        } else if (e.getSource() == btnLogout) {
            //FUNCTION
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SettingsPanel());
        frame.setSize(720, 520);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}