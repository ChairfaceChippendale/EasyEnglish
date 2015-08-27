package com.ujujzk.easyenglish.eeapp;


import java.util.List;

public class Topic {

    private String title;
    private List<Task> tasks;
    private String rules;





    public void addTask (Task task) {
        tasks.add(task);
    }

    public Task getTask (int num) {
        return tasks.get(num);
    }

    public int getTasksNumber () {
        return tasks.size();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }


}
