package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ujujzk.easyenglish.eeapp.model.Task;

import java.util.ArrayList;


public class TaskActivity extends Activity {

    Button tip, checkNext;
    ImageView goBack;
    EditText question;
    TextView answer,  taskType;
    WebView rule;

    boolean taskPassed = false;
    int currentTaskNumber;
    int totalTasksNumber;


    ArrayList<Task> aggregateTasksToLearn;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_act);

        aggregateTasksToLearn = (ArrayList<Task>) getIntent().getSerializableExtra(Task.class.getCanonicalName());

        currentTaskNumber = 0;
        totalTasksNumber = aggregateTasksToLearn.size();

        taskType = (TextView) findViewById(R.id.task_act_tv_task_type);
        question = (EditText) findViewById(R.id.task_act_et_question);
        answer = (TextView) findViewById(R.id.task_act_tv_answer);
        rule = (WebView) findViewById(R.id.task_act_tv_rule);

        taskType.setText(aggregateTasksToLearn.get(currentTaskNumber).getTaskType());
        question.setText(aggregateTasksToLearn.get(currentTaskNumber).getQuestion());
        answer.setText(aggregateTasksToLearn.get(currentTaskNumber).getAnswer());

        rule.setBackgroundColor(getResources().getColor(R.color.app_color_main));
        rule.loadData(
                Application.ruleCloudCrudDao.read(aggregateTasksToLearn.get(currentTaskNumber).getRuleId()).getRule(),
                "text/html",
                null);

        tip = (Button) findViewById(R.id.task_act_btn_tip);
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rule.setVisibility(View.VISIBLE);
            }
        });

        checkNext = (Button) findViewById(R.id.task_act_btn_check_next);
        checkNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (taskPassed) {

                    currentTaskNumber++;
                    if (currentTaskNumber >= totalTasksNumber) {
                        finish();
                    } else {

                        taskType.setText(aggregateTasksToLearn.get(currentTaskNumber).getTaskType());
                        question.setText(aggregateTasksToLearn.get(currentTaskNumber).getQuestion());
                        answer.setText(aggregateTasksToLearn.get(currentTaskNumber).getAnswer());
                        rule.loadData(
                                Application.ruleCloudCrudDao.read(aggregateTasksToLearn.get(currentTaskNumber).getRuleId()).getRule(),
                                "text/html",
                                null);

                        answer.setVisibility(View.INVISIBLE);
                        rule.setVisibility(View.INVISIBLE);
                        checkNext.setText(getResources().getString(R.string.task_act_btn_check_text));
                        taskPassed = false;
                    }

                } else {
                    answer.setVisibility(View.VISIBLE);
                    rule.setVisibility(View.VISIBLE);
                    checkNext.setText(getResources().getString(R.string.task_act_btn_next_text));
                    taskPassed = true;
                }

            }
        });

        goBack = (ImageView) findViewById(R.id.task_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }





}
