package com.ujujzk.easyenglish.eeapp;

public class Task {

    private int id;
    private String question;
    private String answer;
    private String rule;
    private boolean isDone;

    public Task(int id, String question, String answer, String rule) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.rule = rule;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
