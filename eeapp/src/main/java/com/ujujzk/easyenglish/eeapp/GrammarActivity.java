package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class GrammarActivity extends Activity {

    Button start;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammar_act);

        start = (Button) findViewById(R.id.gram_act_btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
