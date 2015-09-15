package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.ujujzk.easyenglish.eeapp.model.Card;
import com.ujujzk.easyenglish.eeapp.model.Pack;

import java.util.ArrayList;
import java.util.List;


public class AddWordActivity extends Activity {

    public static final String ADD_WARD_ACT_TAG = "AddWordActivity Tag";

    private Button add;
    private ImageView goBack;

    private EditText packName, frontSide, backSide;
    private Pack newPack;
    private Card newCard;



    private final static int WORD_MIN_LENGTH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word_act);

        newPack = new Pack();

        packName = (EditText) findViewById(R.id.adw_act_et_pack_name);
        frontSide = (EditText) findViewById(R.id.adw_act_et_front_side);
        backSide = (EditText) findViewById(R.id.adw_act_et_back_side);

        add = (Button) findViewById(R.id.adw_act_btn_add_card);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (packName.getText().length() >= WORD_MIN_LENGTH &&
                        frontSide.getText().length() >= WORD_MIN_LENGTH &&
                        backSide.getText().length() >= WORD_MIN_LENGTH) {


                    if (newPack.getTitle().length() < WORD_MIN_LENGTH) {
                        newPack.setTitle("" + packName.getText());
                    }

                    newCard = new Card("" + frontSide.getText(), "" + backSide.getText());
                    newPack.addCard(newCard);

                    frontSide.setText("");
                    backSide.setText("");
                    packName.setEnabled(false);
                    packName.setFocusable(false);

                    Log.d(ADD_WARD_ACT_TAG, "-----------");
                    Log.d(ADD_WARD_ACT_TAG, "Pack title: " + newPack.getTitle());
                    Log.d(ADD_WARD_ACT_TAG, "===========");
                    for (Card c : newPack.getAllCards()) {
                        Log.d(ADD_WARD_ACT_TAG, "Card front: " + c.getFront());
                        Log.d(ADD_WARD_ACT_TAG, "Card front: " + c.getBack());
                    }
                    Log.d(ADD_WARD_ACT_TAG, "-----------");
                }

            }
        });


        goBack = (ImageView) findViewById(R.id.adw_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (newPack.getTitle().length() >= WORD_MIN_LENGTH) {
                    Application.packLocalCrudDao.createWithRelations(newPack);
                }

                finish();
            }
        });

    }
}
