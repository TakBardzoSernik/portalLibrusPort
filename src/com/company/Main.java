package com.company;
import com.company.gui.MainWindow;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static Document htmlDoc;
    public static JFrame mainWindow;
    protected static Login login;

    public static void runSynergia(){
        mainWindow = new JFrame("MainWindow");
        mainWindow.setContentPane(new MainWindow(login).windowPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Librus Synergia");
        mainWindow.setSize(600,400);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        Sync sync = new Sync();
        try { sync.firstSync(login);
        } catch (IOException exception) { exception.printStackTrace(); }
    }

    public static void main(String[] args) {
		login = new Login();
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
