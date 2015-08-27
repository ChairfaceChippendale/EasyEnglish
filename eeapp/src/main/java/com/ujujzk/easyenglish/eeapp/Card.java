package com.ujujzk.easyenglish.eeapp;

public class Card {

    private String front;
    private String back;
    private boolean learned;


    public Card(String front, String back) {

        this.front = front;
        this.back = back;
        this.learned = false;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public boolean isLearned() {
        return learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }
}
