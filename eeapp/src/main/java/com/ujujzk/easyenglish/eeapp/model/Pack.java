package com.ujujzk.easyenglish.eeapp.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pack extends Base {

    private String title;
    private ArrayList<Card> cards;

    public Pack() {
    }

    public Pack(String title, List<Card> cards) {
        this.title = title;
        cards = new ArrayList<Card>(cards);

    }



    private int getCardsNumber () {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }
    public void addCard(Card card) {
        this.cards.add(card);
    }
    public void removeCards() {
        this.cards.clear();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
