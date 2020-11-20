package com.company;
import com.company.category.Oceny;
import  com.company.misc.fHttp;

import org.jsoup.nodes.Element;

import java.io.IOException;


public class Sync {

    public void firstSync(Login login) throws IOException {
        Main.htmlDoc = fHttp.getDocument(login, "https://synergia.librus.pl/przegladaj_oceny/uczen");
        Element user_section = Main.htmlDoc.select("#user-section b").get(1);

        String str = user_section.text();
        //cut string after second space
        login.setUser(str.substring(0, str.indexOf(" ", str.indexOf(" ") + 1)));

        login.oceny = new Oceny(Main.htmlDoc);
    }
}
