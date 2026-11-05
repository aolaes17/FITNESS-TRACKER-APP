/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnesstrackerapp.ui;
/**
 *
 * @author User
 */
public class RegisterForm {
    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author User
 */
public class RegisterForm extends JFrame implements ActionListener{
    JLabel lblTitle, lblName, lblUsername;
    JLabel lblEmail, lblPassword, lblConfirmPassword;
    JTextField tfName, tfUsername, tfEmail;
    JPasswordField pfPassword, pfConfirmPassword;
    JButton btnRegister, btnCancel;
    
    public RegisterForm() {

        setLayout(null);
        setSize(1000, 700);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("CREATE ACCOUNT");
        lblTitle.setBounds(400, 70, 250, 50);
        add(lblTitle);

        lblName = new JLabel("FULL NAME:");
        lblName.setBounds(200, 160, 150, 40);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(400, 160, 250, 40);
        add(tfName);

        lblUsername = new JLabel("USERNAME:");
        lblUsername.setBounds(200, 220, 150, 40);
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(400, 220, 250, 40);
        add(tfUsername);

        lblEmail = new JLabel("EMAIL:");
        lblEmail.setBounds(200, 280, 150, 40);
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(400, 280, 250, 40);
        add(tfEmail);

        lblPassword = new JLabel("PASSWORD:");
        lblPassword.setBounds(200, 340, 150, 40);
        add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(400, 340, 250, 40);
        add(pfPassword);

        lblConfirmPassword = new JLabel("CONFIRM PASSWORD:");
        lblConfirmPassword.setBounds(200, 400, 180, 40);
        add(lblConfirmPassword);

        pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.setBounds(400, 400, 250, 40);
        add(pfConfirmPassword);

        btnRegister = new JButton("SIGN UP");
        btnRegister.setBounds(300, 490, 150, 50);
        add(btnRegister);

        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(470, 490, 150, 50);
        add(btnCancel);
        btnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCancel){
            LoginForm app = new LoginForm();
        app.setVisible(true);

        this.dispose();
        }
    }
}
