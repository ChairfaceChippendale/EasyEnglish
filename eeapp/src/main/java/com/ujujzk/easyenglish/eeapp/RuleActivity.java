package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.ujujzk.easyenglish.eeapp.model.Rule;
import com.ujujzk.easyenglish.eeapp.model.Topic;


public class RuleActivity extends Activity {

    ImageView goBack;

    Rule rule;
    String topicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule_act);

        rule = (Rule) getIntent().getSerializableExtra(Rule.class.getCanonicalName());
        topicId = getIntent().getStringExtra(Topic.class.getCanonicalName());

        Log.d("RuleActTag", "rule: " + rule.getRule());
        Log.d("RuleActTag", "topicId: " + topicId);

        goBack = (ImageView) findViewById(R.id.rule_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
