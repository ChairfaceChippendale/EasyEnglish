package com.ujujzk.easyenglish.eeapp.model;

public class Task extends Base {


    private String taskType;
    private String question;
    private String answer;
    private String rule;
    private boolean isDone;

    public Task() {
    }

    public Task(String taskType, String question, String answer, String rule) {

        this.taskType = taskType;
        this.question = question;
        this.answer = answer;
        this.rule = rule;
        this.isDone = false;
    }



    public String getTaskType() { return taskType; }

    public void setTaskType(String taskType) { this.taskType = taskType; }


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
