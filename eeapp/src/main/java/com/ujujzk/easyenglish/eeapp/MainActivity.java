package com.ujujzk.easyenglish.eeapp;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.parse.ParseObject;


public class MainActivity extends Activity {

    Button grammar, vocabulary;
    ImageView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);



        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();



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



    }





}
