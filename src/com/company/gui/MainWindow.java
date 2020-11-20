package com.company.gui;
import com.company.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class MainWindow {

    public JPanel windowPanel;
    private JTabbedPane tabOceny;
    private JButton logoutButton;
    private JPanel holderOcen;
    private JPanel cellSemestr1;
    private JPanel cellSemestr2;
    private JLabel namePrzedmiot;
    public JLabel user_section;

    private Login login;

    protected void logoutButtonProcess() {
        this.login = new Login();
    }

    public MainWindow(Login login) {
        this.login = login;
        logoutButton.addComponentListener(new ComponentAdapter() {
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.closeMainWindow();
                logoutButtonProcess();
            }
        });
    }
}
