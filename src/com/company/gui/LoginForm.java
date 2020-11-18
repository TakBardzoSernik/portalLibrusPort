package com.company.gui;
import com.company.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LoginForm {
    public JPanel loginFormPanel;
    private JTextField librusLoginTextField;
    private JTextField username;
    private JPasswordField passwordField1;
    private JButton loginButton;

    private void loginProcess(Login login){
        try {
            if(login.newLogin(username.getText(), passwordField1.getPassword())){
                Main.runSynergia();
            }
        } catch (IOException exception) {exception.printStackTrace();};
    };

    public LoginForm(Login login) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginProcess(login);
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginProcess(login);
                }
            }
        });
    }
}
