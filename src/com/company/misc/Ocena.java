package com.company.misc;

import org.jsoup.nodes.Element;

public class Ocena {
    public String value;
    public String color = "#FFFFFF";
    public String details;
    public int iPrzedmiot;

    public void extractSpan(Element span, int iPrzedmiotu){
        this.iPrzedmiot = iPrzedmiotu;
        value = span.text();
        Element a = span.selectFirst("a");
        if ( a!= null) setDetails(a.attr("title"));
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Ocena(){};
    public Ocena(Element span, int iPrzedmiotu){
        extractSpan(span,iPrzedmiotu);
    }
}
