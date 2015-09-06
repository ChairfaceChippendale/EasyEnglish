package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class VocabularyActivity extends Activity {

    Button start;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_act);

        start = (Button) findViewById(R.id.vocab_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
