package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class GrammarActivity extends Activity {

    Button start;
    ImageView goBack;
    ListView topicsList;
    String [] topicsTitles = {"Articles a(an) and the", "Adjectives order"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_act);

        topicsList = (ListView) findViewById(R.id.gram_act_lv_topics_list);
        topicsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> topicsListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                topicsTitles);
        topicsList.setAdapter(topicsListAdapter);
        topicsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //DEFINE RULE ID

                Intent intent = new Intent(GrammarActivity.this, RuleActivity.class);
                startActivity(intent);
            }
        });

        start = (Button) findViewById(R.id.gram_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CHECK WHAT TOPICS WERE SELECTED

                Intent intent = new Intent(GrammarActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        goBack = (ImageView) findViewById(R.id.gram_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
