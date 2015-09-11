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

import com.ujujzk.easyenglish.eeapp.model.Task;
import com.ujujzk.easyenglish.eeapp.model.Topic;

import java.util.ArrayList;
import java.util.List;


public class GrammarActivity extends Activity {

    Button start, readRule;
    ImageView goBack;
    ListView topicsList;
    ArrayList<Task> aggregateTasksToLearn;
    ArrayList<Topic> availableTopics;

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
                //availableTopics = new ArrayList<Topic>(topics);
                topicsListAdapter.addAll(topics);
                topicsListAdapter.notifyDataSetChanged();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);




        topicsList.setAdapter(topicsListAdapter);


        start = (Button) findViewById(R.id.gram_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aggregateTasksToLearn = new ArrayList<Task>();

                SparseBooleanArray checkedPacksPositions = topicsList.getCheckedItemPositions();//USE getCheckedItemIds() INSTED OF getCheckedItemPositions()
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {


                        //COLLECT ALL SELECTED PACKS AND DO WITH THEM SOMETHING
                        Log.d("My TAG", ((Topic) topicsList.getItemAtPosition(key)).getTitle());


                        aggregateTasksToLearn.addAll( Application.topicCloudCrudDao.readWithRelations( ((Topic) topicsList.getItemAtPosition(key)).getObjectId() ).getAllTasks() );

                    }
                }

                if (!aggregateTasksToLearn.isEmpty()) {

                    Intent intent = new Intent(GrammarActivity.this, TaskActivity.class);
                    intent.putExtra(Task.class.getCanonicalName(), aggregateTasksToLearn);
//                    intent.putParcelableArrayListExtra(Task.class.getCanonicalName(), aggregateTasksToLearn);
                    startActivity(intent);

                }
            }
        });

        readRule = (Button) findViewById(R.id.gram_act_btn_rule);
        readRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




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
