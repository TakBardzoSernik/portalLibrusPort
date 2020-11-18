package com.company.category;

import com.company.misc.Ocena;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import com.company.misc.Przedmiot;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Oceny {
    //CONF Search indexes
    final int iTbody        = 5;
    //first td removed//
    final int iSubjectName  = 0;
    final int iTerm1        = 1;
    final int iEndTerm1     = 3;
    final int iTerm2        = 4;
    final int iEndTerm2     = 6;
    final int iFinal        = 7;

    List<Ocena> ocenyS1 = new ArrayList<>();
    List<Ocena> ocenyS2 = new ArrayList<>();
    Ocena semestralna1;
    Ocena semestralna2;
    Ocena koncowa;
    Przedmiot[] przedmioty;

    private void ocenySemestru(List<Ocena> semestr, Elements cells, int searchIndex, int iPrzedmiot){
        Elements spans = cells.get(searchIndex).select("span");
        for(Element span : spans) {
            semestr.add(new Ocena(span, iPrzedmiot));
        };
    }
    private void ocenaIndywidualna(Ocena ocena,  Elements cells, int searchIndex, int iPrzedmiot){
        Element span = cells.get(searchIndex).selectFirst("span");
        if(span != null) ocena = new Ocena(span, iPrzedmiot);
    }

    public void collectData(Document htmlDoc){

        Element table =  htmlDoc.select("tbody").get(iTbody);
        Elements rows = table.select(">tr:not([name])");
        przedmioty = new Przedmiot[rows.size()];
        int i = 0;
        System.out.println("Liczba przedmiotów: "+rows.size());

        for (Element row : rows)  {
            przedmioty[i] = new Przedmiot();
            Elements cells = row.select(">td:not([class])");

            //Nazwa przedmiotu
            przedmioty[i].name = cells.get(iSubjectName).text();

            //Oceny Semestr 1
            ocenySemestru(ocenyS1, cells, iTerm1, i);

            //Ocena Semestralna 1
            if(cells.size() == 3) break;
            ocenaIndywidualna(semestralna1, cells, iEndTerm1, i);

            //Oceny Semestr 2
            //ocenySemestru(ocenyS2, cells, iTerm2, i);

            //Ocena Semestralna 2
            //ocenaIndywidualna(semestralna2, cells, iEndTerm2, i);

            //Ocena Końcowa
            //ocenaIndywidualna(koncowa, cells, iFinal, i);
            i++;
        };

        //Print semestr 1
        int pCounter = 0;
        int oCounter = 0;
        for(Przedmiot przedmiot : przedmioty) {
            System.out.print("\n"+przedmiot.name+": ");
            while(oCounter < ocenyS1.size() && ocenyS1.get(oCounter).iPrzedmiot == pCounter) {
                System.out.print(ocenyS1.get(oCounter).value + " ");
                oCounter++;
            }
            pCounter++;
        }
    }
    public void Oceny(Document htmlDoc){
        collectData(htmlDoc);
    }
}
