package com.pacman.Headbud.ui.home;

public class QuotesModel {
    public String[] mQuotes = {
            "Do not give up",
            "Love yourself",
            "Happiness comes from within",
            "You are not alone",
            "Put yourself first"

    };

    public String getQuote(int i) {
        String quote = mQuotes[i];
        return quote;
    }

    public int getLength(){
        int len = mQuotes.length;
        return len;
    }
}
