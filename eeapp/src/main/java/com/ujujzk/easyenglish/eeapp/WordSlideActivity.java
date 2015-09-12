package com.ujujzk.easyenglish.eeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ujujzk.easyenglish.eeapp.model.Card;
import com.ujujzk.easyenglish.eeapp.service.PronunciationService;

import java.util.ArrayList;


public class WordSlideActivity extends Activity {

    private static final boolean WORD_IS_LEARNED = true;
    private static final boolean WORD_IS_NOT_LEARNED = false;

    private static final boolean FRONT_SIDE = true;
    private static final boolean BACK_SIDE = false;

    private static final int STEPS_BACK = 4;

    private ArrayList<Card> aggregateCardsToLearn;

    private ImageView goBack;
    private ImageView prononciation;
    private int currentCard;

    private boolean side;

    private String wordToPronounce;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_slide_act);

        aggregateCardsToLearn = (ArrayList<Card>) getIntent().getSerializableExtra(Card.class.getCanonicalName());

        currentCard = 0;
        side = FRONT_SIDE;


        FrameLayout parentLayoutForWordLearn = (FrameLayout) findViewById(R.id.wslide_act_fl_parent_layout_for_word_learn);
        parentLayoutForWordLearn.addView(new WordSlideLearnView(this));

        goBack = (ImageView) findViewById(R.id.wslide_act_img_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        prononciation = (ImageView) findViewById(R.id.wslide_act_img_prononciation);
        prononciation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //word pronunciation
                //powered by Google
                //https://ssl.gstatic.com/dictionary/static/sounds/de/0/WORD.mp3
                wordToPronounce = aggregateCardsToLearn.get(currentCard).getBack();

                if(side == BACK_SIDE && !wordToPronounce.isEmpty()){

                    wordToPronounce.replace(" ", "_").toLowerCase();
                    Intent intent = new Intent(PronunciationService.PRONUNCIATION_TASK);
                    intent.putExtra(PronunciationService.WORD, wordToPronounce);
                    sendBroadcast(intent);

                }

            }
        });

    }

    class WordSlideLearnView extends View {

        private final static int MOVE_UP = 2;
        private final static int MOVE_DOWN = 1;
        private final static int DO_NOT_MOVE = 0;

        private Paint mPaint = new Paint();
        private Rect mTextBoundRect = new Rect();

        private float width, height, centerX, centerY;
        private String text = "word";

        private int selfMoveDirection = 0;

        private float dragY = 0;

        public WordSlideLearnView(Context context) {
            super(context);
            this.setWillNotDraw(false);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //Log.d("MyTag", "Y: " + centerY);

            //screen sizes (in fact it is just View sizes)
            width = getWidth();
            height = getHeight();

            //set the text on the horizontal center
            centerX = width / 2;

            //set the text on the vertical center (works at the start)
            if (centerY == 0) {
                centerY = height / 2;
            }

            //make a background
            //mPaint.setColor(getResources().getColor(R.color.app_color_main));
            //canvas.drawPaint(mPaint);

            //make the text
            float mTextWidth, mTextHeight;
            mPaint.setColor(getResources().getColor(R.color.app_color_black));
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(50);
            //sizes of rectangle that is made by the text
            mPaint.getTextBounds(text, 0, text.length(), mTextBoundRect);
            mTextWidth = mPaint.measureText(text);
            mTextHeight = mTextBoundRect.height();
            //draw the text
            canvas.drawText(text,
                    centerX - (mTextWidth / 2f),
                    centerY + (mTextHeight /2f),
                    mPaint
            );

            try {

                if (selfMoveDirection == WordSlideLearnView.MOVE_UP) {


                    centerY -= 30;
                    //TimeUnit.MILLISECONDS.sleep(1);
                    invalidate();

                    if (centerY < 1) {
                        selfMoveDirection = WordSlideLearnView.DO_NOT_MOVE;
                        nextWordSlideLearnView(WORD_IS_LEARNED);
                        centerY = height / 2;
                        invalidate();
                    }

                } else if (selfMoveDirection == WordSlideLearnView.MOVE_DOWN) {

                    //Log.d("MyTag", "downY: " + centerY);

                    centerY += 30;
                    //TimeUnit.MILLISECONDS.sleep(1);
                    invalidate();

                    if (centerY > height) {
                        selfMoveDirection = WordSlideLearnView.DO_NOT_MOVE;
                        nextWordSlideLearnView(WORD_IS_NOT_LEARNED);
                        centerY = height / 2;
                        invalidate();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public synchronized boolean onTouchEvent(MotionEvent event) {

            // define Y-coordinate of the Touch-event
            float evY = event.getY();

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    dragY = evY - centerY; //difference between the Touch-event and the center of the text
                    break;

                case MotionEvent.ACTION_MOVE:

                    centerY = evY - dragY;  //define new center of the text
                    invalidate();           //re-draw the text
                    break;

                case MotionEvent.ACTION_UP:

                    //Log.d("MyTag", "2w: "+width);
                    //Log.d("MyTag", "2h: "+height);

                    if ( (evY - dragY) < (height/4) ) { //upper quoter of the screen "I KNOW"

                        selfMoveDirection = WordSlideLearnView.MOVE_UP;
                        invalidate();
                        //nextWordSlideLearnView();

                    } else if ((evY - dragY) > (height*3/4)) { //lower quoter of the screen "I FORGOT"

                        selfMoveDirection = WordSlideLearnView.MOVE_DOWN;
                        invalidate();

                        //Log.d("MyTag", "downY: "+centerY);
                        //nextWordSlideLearnView();

                    } else if (Math.abs(centerY - height / 2) < 20) {

                        translateWordSlideLearnView ();

                    } else {
                        centerY = height / 2;
                    }

                    //centerY = height / 2;       //return the text' center to the center of the screen
                    invalidate();               //re-draw the text
                    break;
            }
            return true;
        }



        private void nextWordSlideLearnView (boolean isLearned) {

            //опнбепхрэ х рейсыхи хмдейя х оепемня якнбю

            if (!isLearned) {
                aggregateCardsToLearn.add(aggregateCardsToLearn.size(), aggregateCardsToLearn.get(currentCard));
            }
            currentCard++;
            text = aggregateCardsToLearn.get(currentCard).getFront();

            //!!------------------------------------------------------

        }

        private void translateWordSlideLearnView () {

            if (side == FRONT_SIDE) {
                text = aggregateCardsToLearn.get(currentCard).getBack();
                side = BACK_SIDE;
            } else {
                text = aggregateCardsToLearn.get(currentCard).getFront();
                side = FRONT_SIDE;
            }

        }




    }
}
