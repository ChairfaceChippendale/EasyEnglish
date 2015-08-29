package com.ujujzk.easyenglish.eeapp;

import com.parse.Parse;
import com.ujujzk.easyenglish.eeapp.dao.CrudDao;
import com.ujujzk.easyenglish.eeapp.dao.parse.ParseCloudCrudDaoImpl;
import com.ujujzk.easyenglish.eeapp.dao.parse.ParseLocalCrudDaoImpl;
import com.ujujzk.easyenglish.eeapp.model.Card;

public class Application extends android.app.Application {

    public static CrudDao<Card, String> cardLocalCrudDao;
    public static CrudDao<Card, String> cardCloudCrudDao;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "a2FaVXXRxCiY0r61U0nZ6hS6VhuSDcQfC32Vhium", "b2aaFgro20MWP8t1sRGbjdsRrJrwBBm78cSDKxD8");

        cardLocalCrudDao = new ParseLocalCrudDaoImpl<Card>(Card.class);
        cardCloudCrudDao = new ParseCloudCrudDaoImpl<Card>(Card.class);


    }
}
