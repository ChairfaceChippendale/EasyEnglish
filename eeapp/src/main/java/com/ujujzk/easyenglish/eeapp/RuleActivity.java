package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ujujzk.easyenglish.eeapp.model.Rule;
import com.ujujzk.easyenglish.eeapp.model.Task;
import com.ujujzk.easyenglish.eeapp.model.Topic;

import java.util.ArrayList;


public class RuleActivity extends Activity {

    ImageView goBack;
    TextView topicTitle;
    WebView ruleText;
    Button startTest;

    Rule rule;
    String topicId;
    ArrayList<Task> tasksToLearn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule_act);

        topicTitle = (TextView) findViewById(R.id.rule_act_tv_title);
        topicTitle.setText(getIntent().getStringExtra(GrammarActivity.TOPIC_TITLE));

        rule = (Rule) getIntent().getSerializableExtra(GrammarActivity.RULE);
        ruleText = (WebView) findViewById(R.id.rule_act_wv_rule);
        ruleText.setBackgroundColor(getResources().getColor(R.color.app_color_main));
        ruleText.loadData(rule.getRule(), "text/html", null);

        topicId = getIntent().getStringExtra(GrammarActivity.TOPIC_ID);

        //Log.d("RuleActTag", "rule: " + rule.getRule());
        Log.d("RuleActTag", "topicId: " + topicId);

        goBack = (ImageView) findViewById(R.id.rule_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        startTest = (Button) findViewById(R.id.rule_act_btn_start);
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tasksToLearn = new ArrayList<Task>();
                tasksToLearn.addAll(
                        Application.topicCloudCrudDao.readWithRelations(topicId).getAllTasks()
                );

                if (!tasksToLearn.isEmpty()) {
                    Intent intent = new Intent(RuleActivity.this, TaskActivity.class);
                    intent.putExtra(Task.class.getCanonicalName(), tasksToLearn);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }



}
