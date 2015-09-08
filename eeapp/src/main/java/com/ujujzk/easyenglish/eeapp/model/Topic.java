package com.ujujzk.easyenglish.eeapp.model;


import java.util.ArrayList;
import java.util.List;

public class Topic extends Base {

    private String title;
    private ArrayList<Task> tasks;
    private String rules;


    public Topic() {
    }

    public Topic(String title, ArrayList<Task> tasks, String rules) {
        this.title = title;
        tasks = new ArrayList<Task>(tasks);
        this.rules = rules;
    }

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
