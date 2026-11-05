package com.mycompany.fitnesstrackerapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginFormUI extends JFrame implements ActionListener {

    JLabel lblUsername, lblpassword, lblTitle;
    JPasswordField pfpassword;
    JTextField tfusername;
    JButton btnLogin, btnRegister;

    public LoginFormUI() {
        setLayout(null);
        setSize(1000, 700);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTitle = new JLabel("FITNESS TRACKER");
        lblTitle.setBounds(350, 100, 300, 50);
        add(lblTitle);

        lblUsername = new JLabel("USERNAME:");
        lblUsername.setBounds(250, 250, 150, 50);
        add(lblUsername);

        tfusername = new JTextField();
        tfusername.setBounds(400, 250, 250, 50);
        add(tfusername);

        lblpassword = new JLabel("PASSWORD:");
        lblpassword.setBounds(250, 330, 150, 50);
        add(lblpassword);

        pfpassword = new JPasswordField();
        pfpassword.setBounds(400, 330, 250, 50);
        add(pfpassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(300, 430, 150, 50);
        add(btnLogin);
        btnLogin.addActionListener(this);

        btnRegister = new JButton("REGISTER");
        btnRegister.setBounds(470, 430, 150, 50);
        add(btnRegister);
        btnRegister.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            RegisterForm app = new RegisterForm();
            app.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btnLogin) {
            
            MainDashboard app = new MainDashboard();
            app.setVisible(true);
            this.dispose();
        }
    }
}