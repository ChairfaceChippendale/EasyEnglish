package com.ujujzk.easyenglish.eeapp.dao.parse;

import com.ujujzk.easyenglish.eeapp.model.Base;

public class ParseLocalCrudDaoImpl<Model extends Base> extends AbstractParseCrudDaoImpl<Model> {

    public ParseLocalCrudDaoImpl(Class clazz) {
        super(false, clazz);
    }
}
