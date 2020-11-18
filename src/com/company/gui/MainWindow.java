package com.company.gui;
import com.company.Login;
import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class MainWindow {

    public static JComponent getComponentByName(Container root, String name) {
        for (int i = 0; i < root.getComponentCount(); i++) {
            if (root.getComponent(i).getName() != null && root.getComponent(i).getName().equals(name)) {
                return (JComponent) root.getComponent(i);
            }
        }
        //ERROR
        return (JComponent) root.getComponent(0);
    }

    public JPanel windowPanel;
    private JTabbedPane tabOceny;
    private JButton logoutButton;
    private JPanel holderOcen;
    private JPanel cellSemestr1;
    private JPanel cellSemestr2;
    private JLabel namePrzedmiot;
    private JLabel user_section;

    private Login login;

    protected void logoutButtonProcess() {
        login = new Login();
    }

    public MainWindow(Login login) {
        this.login = login;
        logoutButton.addComponentListener(new ComponentAdapter() {
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButtonProcess();
                Main.mainWindow.dispose();
            }
        });
    }
}
