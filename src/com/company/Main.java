package com.company;
import com.company.gui.GuiManager;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static Document htmlDoc;
    protected static Login login;

    public static void runSynergia(){
        GuiManager.openMainWindow(login);
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
