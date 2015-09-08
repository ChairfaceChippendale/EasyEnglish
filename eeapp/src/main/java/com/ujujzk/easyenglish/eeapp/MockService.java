package com.ujujzk.easyenglish.eeapp;

import com.ujujzk.easyenglish.eeapp.model.Card;
import com.ujujzk.easyenglish.eeapp.model.Pack;
import com.ujujzk.easyenglish.eeapp.model.Task;
import com.ujujzk.easyenglish.eeapp.model.Topic;
import java.util.ArrayList;


public class MockService {


    Card getCard() {
        return new Card("слово", "word");
    }

    Pack getPack(){

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(getCard());
        cards.add(new Card("кот","cat"));
        cards.add(new Card("собака", "dog"));

        return new Pack("New Pack", cards);
    }

    Task getTask() {
      return new Task("Complete sentence:",
              "This is .. book. It is my .. book.",
              "This is a book. It is my book.",
              "Do not use article \"a\" after \"my\".");
    }

    Topic getTopic () {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(getTask());
        tasks.add(new Task("Complete sentence:",
                "These are pencils. ... pencils are black.",
                "These are pencils. The pencils are black.",
                "Do not use \"a\" with plural."));

        return new Topic("New Topic", tasks, "Full rule");
    }

}
