package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import com.ujujzk.easyenglish.eeapp.model.Pack;

import java.util.ArrayList;


public class VocabularyActivity extends Activity {

    Button start;
    ImageView goBack;
    ListView packsList;
    ArrayList<Pack> packs;
    //String [] packsTitles = {"U2","U3","U4","Cats","U6A","Cats tails"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_act);

        packs = new ArrayList<Pack>();
        packs.add(MockService.getPack());
        packs.add(MockService.getPack());



        packsList = (ListView) findViewById(R.id.vocab_act_lv_packs_list);
        packsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);



        ArrayAdapter<Pack> packsListAdapter = new ArrayAdapter<Pack>(this,
                android.R.layout.simple_list_item_multiple_choice,
                packs);






        packsList.setAdapter(packsListAdapter);



        start = (Button) findViewById(R.id.vocab_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check what packs were selected
                SparseBooleanArray checkedPacksPositions = packsList.getCheckedItemPositions();
                for (int i = 0; i < checkedPacksPositions.size(); i++) {
                    int key = checkedPacksPositions.keyAt(i);
                    if (checkedPacksPositions.get(key)) {

                        //COLLECT ALL SELECTED PACKS AND DO WITH THEM SOMETHING
                        Log.d("My TAG", packs.get(key).getTitle());

                    }
                }

                Intent intent = new Intent(VocabularyActivity.this, WordSlideActivity.class);
                startActivity(intent);
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


}
