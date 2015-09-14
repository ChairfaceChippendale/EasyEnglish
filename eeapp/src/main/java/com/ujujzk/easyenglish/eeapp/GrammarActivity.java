package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.ujujzk.easyenglish.eeapp.model.Rule;
import com.ujujzk.easyenglish.eeapp.model.Task;
import com.ujujzk.easyenglish.eeapp.model.Topic;

import java.util.ArrayList;
import java.util.List;


public class GrammarActivity extends Activity {

    public static final String RULE = "ruleForRuleActivity";
    public static final String TOPIC_ID = "topicIdForRuleActivity";
    public static final String TOPIC_TITLE = "topicTitleForRuleActivity";

    Button start, readRule;
    ImageView goBack;
    ListView topicsList;
    private ProgressBar progressBar;

    ArrayList<Task> aggregateTasksToLearn;

    Rule rule;
    String topicId;
    String topicTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_act);

        progressBar = (ProgressBar) findViewById(R.id.gram_act_prorgress_bar);

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

                progressBar.setVisibility(View.GONE);
                topicsList.setVisibility(View.VISIBLE);

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        topicsList.setAdapter(topicsListAdapter);

        start = (Button) findViewById(R.id.gram_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aggregateTasksToLearn = new ArrayList<Task>();

                SparseBooleanArray checkedPacksPositions = topicsList.getCheckedItemPositions();
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {

                        aggregateTasksToLearn.addAll(
                                Application.topicCloudCrudDao.readWithRelations(
                                        ((Topic) topicsList.getItemAtPosition(key)).getObjectId()
                                ).getAllTasks()
                        );
                    }
                }

                if (!aggregateTasksToLearn.isEmpty()) {
                    Intent intent = new Intent(GrammarActivity.this, TaskActivity.class);
                    intent.putExtra(Task.class.getCanonicalName(), aggregateTasksToLearn);
                    startActivity(intent);
                }
            }
        });

        readRule = (Button) findViewById(R.id.gram_act_btn_rule);
        readRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checkedPacksPositions = topicsList.getCheckedItemPositions();
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {

                        try {
                            rule = Application.ruleCloudCrudDao.readThrowException(
                                    ((Topic) topicsList.getItemAtPosition(key)).getRuleId()
                            );
                        } catch (Exception e){
                            Log.d(getClass().getName(), e == null ? "dao error" : e.getMessage(), e);
                        }

                        topicId = ( (Topic)topicsList.getItemAtPosition(key) ).getObjectId();
                        topicTitle = ((Topic) topicsList.getItemAtPosition(key)).getTitle();

                        //Log.d("GarmmActTag", "rule: " + rule.getRule());
                        Log.d("GarmmActTag", "topicId: " + topicId);
                        break;
                    }
                }

                if (rule != null){
                    Intent intent = new Intent(GrammarActivity.this, RuleActivity.class);
                    intent.putExtra(TOPIC_TITLE, topicTitle);
                    intent.putExtra(RULE, rule);
                    intent.putExtra(TOPIC_ID, topicId);
                    startActivity(intent);
                }
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
