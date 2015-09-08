package com.ujujzk.easyenglish.eeapp;

import com.parse.Parse;
import com.ujujzk.easyenglish.eeapp.dao.CrudDao;
import com.ujujzk.easyenglish.eeapp.dao.parse.ParseCloudCrudDaoImpl;
import com.ujujzk.easyenglish.eeapp.dao.parse.ParseLocalCrudDaoImpl;
import com.ujujzk.easyenglish.eeapp.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Application extends android.app.Application {

    public static CrudDao<Card, String> cardLocalCrudDao;
    public static CrudDao<Card, String> cardCloudCrudDao;

    public static CrudDao<Pack, String> packLocalCrudDao;
    public static CrudDao<Pack, String> packCloudCrudDao;

    public static CrudDao<Task, String> taskLocalCrudDao;
    public static CrudDao<Task, String> taskCloudCrudDao;

    public static CrudDao<Topic, String> topicLocalCrudDao;
    public static CrudDao<Topic, String> topicCloudCrudDao;

    public static CrudDao<Rule, String> ruleLocalCrudDao;
    public static CrudDao<Rule, String> ruleCloudCrudDao;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "a2FaVXXRxCiY0r61U0nZ6hS6VhuSDcQfC32Vhium", "b2aaFgro20MWP8t1sRGbjdsRrJrwBBm78cSDKxD8");

        cardLocalCrudDao = new ParseLocalCrudDaoImpl<Card>(Card.class);
        cardCloudCrudDao = new ParseCloudCrudDaoImpl<Card>(Card.class);

        packLocalCrudDao = new ParseLocalCrudDaoImpl<Pack>(Pack.class);
        packCloudCrudDao = new ParseCloudCrudDaoImpl<Pack>(Pack.class);

        taskLocalCrudDao = new ParseLocalCrudDaoImpl<Task>(Task.class);
        taskCloudCrudDao = new ParseCloudCrudDaoImpl<Task>(Task.class);

        topicLocalCrudDao = new ParseLocalCrudDaoImpl<Topic>(Topic.class);
        topicCloudCrudDao = new ParseCloudCrudDaoImpl<Topic>(Topic.class);

        ruleLocalCrudDao = new ParseLocalCrudDaoImpl<Rule>(Rule.class);
        ruleCloudCrudDao = new ParseCloudCrudDaoImpl<Rule>(Rule.class);


        boolean createMode = false;
        if(createMode){

            packCloudCrudDao.createWithRelations(new Pack("Title", Arrays.asList(new Card[]{new Card("1", "2"), new Card("1", "2")})));
            topicCloudCrudDao.createWithRelations(new Topic("Title", Arrays.asList(new Task[]{new Task("1", "2","3", "4"), new Task("1", "2","3", "4")}), "no rule"));
            ruleCloudCrudDao.createWithRelations(new Rule("<body>...."));

        }
    }
}
