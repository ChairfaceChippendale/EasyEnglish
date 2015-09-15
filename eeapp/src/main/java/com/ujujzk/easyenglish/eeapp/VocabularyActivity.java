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
import com.ujujzk.easyenglish.eeapp.model.Card;
import com.ujujzk.easyenglish.eeapp.model.Pack;

import java.util.ArrayList;
import java.util.List;


public class VocabularyActivity extends Activity {

    Button start;
    ImageView goBack;
    ImageView addPack;
    ImageView removePack;
    ListView packsList;
    private ProgressBar progressBar;

    private ArrayList<Card> aggregateCardsToLearn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_act);
/*
        packs = new ArrayList<Pack>();
        packs.add(MockService.getPack());
        packs.add(MockService.getPack());
*/

        progressBar = (ProgressBar) findViewById(R.id.vocab_act_prorgress_bar);

        packsList = (ListView) findViewById(R.id.vocab_act_lv_packs_list);
        packsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
/*
        final ArrayAdapter<Pack> packsListAdapter = new ArrayAdapter<Pack>(this,
                android.R.layout.simple_list_item_multiple_choice){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Pack pack = getItem(position);
                ((CheckedTextView) view).setText(pack.getTitle());
                return view;
            }
        };

        new AsyncTask<Void, Void, List<Pack>>(){
            @Override
            protected List<Pack> doInBackground(Void... params) {
                return Application.packLocalCrudDao.readAll();
            }
            @Override
            protected void onPostExecute(List<Pack> topics) {

                packsListAdapter.addAll(topics);
                packsListAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
                packsList.setVisibility(View.VISIBLE);

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        packsList.setAdapter(packsListAdapter);
*/

        packsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;

                //TODO
                //http://developer.android.com/guide/topics/ui/menus.html

            }
        });


        addPack = (ImageView) findViewById(R.id.vocab_act_img_add);
        addPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(VocabularyActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });

        removePack = (ImageView) findViewById(R.id.vocab_act_img_remove);
        removePack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checkedPacksPositions = packsList.getCheckedItemPositions();
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {

                        Application.packLocalCrudDao.deleteWithRelations(
                                ((Pack) packsList.getItemAtPosition(key)).getObjectId()
                        );
                        onResume();


                    }
                }
            }
        });

        start = (Button) findViewById(R.id.vocab_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aggregateCardsToLearn = new ArrayList<Card>();

                //Check what packs were selected
                SparseBooleanArray checkedPacksPositions = packsList.getCheckedItemPositions();
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {

                        //COLLECT ALL SELECTED PACKS AND DO WITH THEM SOMETHING
                        aggregateCardsToLearn.addAll(
                                Application.packLocalCrudDao.readWithRelations(
                                        ((Pack) packsList.getItemAtPosition(key)).getObjectId()
                                ).getAllCards()
                        );

                    }
                }

                Log.d("VoabAct Tag",(aggregateCardsToLearn.isEmpty()?"empty":"full"));

                if (!aggregateCardsToLearn.isEmpty()) {
                    Intent intent = new Intent(VocabularyActivity.this, WordSlideActivity.class);
                    intent.putExtra(Card.class.getCanonicalName(), aggregateCardsToLearn);
                    startActivity(intent);
                }
            }
        });



        goBack = (ImageView) findViewById(R.id.vocab_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final ArrayAdapter<Pack> packsListAdapter = new ArrayAdapter<Pack>(this,
                android.R.layout.simple_list_item_multiple_choice){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Pack pack = getItem(position);
                ((CheckedTextView) view).setText(pack.getTitle());
                return view;
            }
        };

        new AsyncTask<Void, Void, List<Pack>>(){
            @Override
            protected List<Pack> doInBackground(Void... params) {
                return Application.packLocalCrudDao.readAll();
            }
            @Override
            protected void onPostExecute(List<Pack> topics) {

                packsListAdapter.addAll(topics);
                packsListAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
                packsList.setVisibility(View.VISIBLE);

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        packsList.setAdapter(packsListAdapter);

    }
}
