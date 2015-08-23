package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class TaskActivity extends Activity {

    Button tip, checkNext;
    ImageView goBack;
    EditText question;
    TextView answer, rule, taskType;
    boolean taskPassed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_act);

        taskType = (TextView) findViewById(R.id.task_act_tv_task_type);
        question = (EditText) findViewById(R.id.task_act_et_question);
        answer = (TextView) findViewById(R.id.task_act_tv_answer);
        rule = (TextView) findViewById(R.id.task_act_tv_rule);

        tip = (Button) findViewById(R.id.task_act_btn_tip);
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
                builder.setTitle("Tip")
                        .setMessage(rule.getText())
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        checkNext = (Button) findViewById(R.id.task_act_btn_check_next);
        checkNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (taskPassed) {
                    answer.setVisibility(View.INVISIBLE);
                    rule.setVisibility(View.INVISIBLE);
                    checkNext.setText(getResources().getString(R.string.task_act_btn_check_text));
                    taskPassed = false;
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
