package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddWordActivity extends Activity {

    private Button add;
    private EditText packName, frontSide, backSide;

    private final static int WORD_MIN_LENGTH = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word_act);

        add = (Button) findViewById(R.id.adw_act_btn_add_card);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (    packName.getText().length() >= WORD_MIN_LENGTH &&
                        frontSide.getText().length() >= WORD_MIN_LENGTH &&
                        backSide.getText().length() >= WORD_MIN_LENGTH) {



                }
            }
        });



    }



}
