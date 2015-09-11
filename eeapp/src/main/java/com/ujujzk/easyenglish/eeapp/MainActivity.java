package com.ujujzk.easyenglish.eeapp;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    Button grammar, vocabulary;
    ImageView goBack, settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        grammar = (Button) findViewById(R.id.main_act_btn_grammar);
        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GrammarActivity.class);
                startActivity(intent);
            }
        });

        vocabulary = (Button) findViewById(R.id.main_act_btn_vocabulary);
        vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VocabularyActivity.class);
                startActivity(intent);
            }
        });

        goBack = (ImageView) findViewById(R.id.main_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        settings = (ImageView) findViewById(R.id.main_act_img_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }





}
