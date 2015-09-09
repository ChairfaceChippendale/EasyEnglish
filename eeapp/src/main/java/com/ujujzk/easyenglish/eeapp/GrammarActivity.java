package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.ujujzk.easyenglish.eeapp.model.Topic;

import java.util.List;


public class GrammarActivity extends Activity {

    Button start;
    ImageView goBack;
    ListView topicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_act);

        topicsList = (ListView) findViewById(R.id.gram_act_lv_topics_list);
        topicsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final ArrayAdapter<Topic> topicsListAdapter = new ArrayAdapter<Topic>(this,
                android.R.layout.simple_list_item_multiple_choice){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);
                Topic topic = getItem(position);
                ((CheckedTextView) view).setText(topic.getTitle());
                return view;
            }
        };


        new AsyncTask<Void, Void, List<Topic>>(){
            @Override
            protected List<Topic> doInBackground(Void... params) {
                return Application.topicCloudCrudDao.readAll();
            }
            @Override
            protected void onPostExecute(List<Topic> topics) {
                topicsListAdapter.addAll(topics);
                topicsListAdapter.notifyDataSetChanged();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);




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
