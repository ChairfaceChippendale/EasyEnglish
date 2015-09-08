package com.ujujzk.easyenglish.eeapp;

import com.ujujzk.easyenglish.eeapp.model.Card;
import com.ujujzk.easyenglish.eeapp.model.Pack;
import com.ujujzk.easyenglish.eeapp.model.Task;
import com.ujujzk.easyenglish.eeapp.model.Topic;
import java.util.ArrayList;
import java.util.List;


public class MockService {


    public static Card getCard() {
        return new Card("слово", "word");
    }

    public static Pack getPack(){

        Application.packLocalCrudDao.delete("EmE69mBbXV");
        Application.packLocalCrudDao.delete("cPls9FlPIt");
        Application.packCloudCrudDao.delete("EmE69mBbXV");
        Application.packCloudCrudDao.delete("cPls9FlPIt");

        List<Pack> list = Application.packLocalCrudDao.readAll();

        Pack result;

        if(list == null || list.isEmpty()){
            ArrayList<Card> cards = new ArrayList<Card>();
            cards.add(getCard());
            cards.add(new Card("кот","cat"));
            cards.add(new Card("собака", "dog"));
            Pack pack = new Pack("New Pack", cards);

            pack = Application.packCloudCrudDao.createWithRelations(pack);
            result = Application.packLocalCrudDao.createWithRelations(pack);
        } else {
            result = list.get(0);
        }

        return result;
    }

    public static Task getTask() {
      return new Task("Complete sentence:",
              "This is .. book. It is my .. book.",
              "This is a book. It is my book.",
              "Do not use article \"a\" after \"my\".");
    }

    public static Topic getTopic () {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(getTask());
        tasks.add(new Task("Complete sentence:",
                "These are pencils. ... pencils are black.",
                "These are pencils. The pencils are black.",
                "Do not use \"a\" with plural."));

        return new Topic("New Topic", tasks, "Full rule");
    }

}
