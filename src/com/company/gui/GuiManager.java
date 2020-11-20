package com.company.gui;

import com.company.Login;
import javax.swing.*;

public class GuiManager {
    private static JFrame mainWindow;
    private static JFrame loginWindow;
    private static MainWindow mainWindowClass;

    public static void openLoginWindow(Login login){
        loginWindow = new JFrame("LoginForm");
        loginWindow.setContentPane(new LoginForm(login).loginFormPanel);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setTitle("Librus Login");
        loginWindow.pack();
        loginWindow.setLocationRelativeTo(null);
        loginWindow.setVisible(true);
    }
    public static void closeLoginWindow() {
        loginWindow.dispose();
    }
    public static void openMainWindow(Login login){
        mainWindowClass = new MainWindow(login);
        mainWindow = new JFrame("MainWindow");
        mainWindow.setContentPane(mainWindowClass.windowPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Librus Synergia");
        mainWindow.setSize(600,400);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    public static void closeMainWindow() {
        mainWindow.dispose();
    }
    public static void setUserName(String name) {
        mainWindowClass.user_section.setText(name);
        mainWindow.revalidate();
    }


    /*
    private static JComponent getComponentByName(Container root, String name) {
        for (int i = 0; i < root.getComponentCount(); i++) {
            if (root.getComponent(i).getName() != null && root.getComponent(i).getName().equals(name)) {
                return (JComponent) root.getComponent(i);
            }
        }
        //Not Found
        return null;
    }
    private static JComponent getComponentByNameRecursive(Container root, String name) {
        Component child;
        Container childAsRoot; //nice name
        JComponent result;

        for (int i = 0; i < root.getComponentCount(); i++) {
            child = root.getComponent(i);
            childAsRoot= (Container)child;
            if (child.getName() != null && child.getName().equals(name)) {
                return (JComponent) child;
            }
            //Recursive
            if(childAsRoot.getComponentCount() > 0){
                for (int r = 0; r < childAsRoot.getComponentCount(); r++){
                    if((result = getComponentByNameRecursive(childAsRoot, name)) != null){
                        return result;
                    }
                }
            }
        }
        //Not Found
        return null;
    }
     */
}
