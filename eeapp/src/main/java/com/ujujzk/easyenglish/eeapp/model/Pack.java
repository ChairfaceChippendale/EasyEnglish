package com.ujujzk.easyenglish.eeapp.model;


import java.util.List;

public class Pack extends Base {

    private String title;
    private List<Card> cards;

    public Pack() {
    }

    public void addCard (Card newCard) {
        cards.add(newCard);
    }

    private int getCardsNumber () {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
