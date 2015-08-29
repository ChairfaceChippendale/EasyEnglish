package com.ujujzk.easyenglish.eeapp.model;


public class Settings extends Base {

    private int fontSize;
    private int mainColor;

    public Settings() {
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getMainColor() {
        return mainColor;
    }

    public void setMainColor(int mainColor) {
        this.mainColor = mainColor;
    }
}
