package com.ujujzk.easyenglish.eeapp.dao.parse;

import com.ujujzk.easyenglish.eeapp.model.Base;

public class ParseCloudCrudDaoImpl<Model extends Base> extends AbstractParseCrudDaoImpl<Model> {

    public ParseCloudCrudDaoImpl(Class clazz) {
        super(true, clazz);
    }
}
