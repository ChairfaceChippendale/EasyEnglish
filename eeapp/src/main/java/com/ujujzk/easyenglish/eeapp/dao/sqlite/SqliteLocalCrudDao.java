package com.ujujzk.easyenglish.eeapp.dao.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ujujzk.easyenglish.eeapp.dao.CrudDao;
import com.ujujzk.easyenglish.eeapp.dao.KeyValue;
import com.ujujzk.easyenglish.eeapp.dao.exception.CannotCreateException;
import com.ujujzk.easyenglish.eeapp.dao.exception.DataNotFoundException;
import com.ujujzk.easyenglish.eeapp.dao.exception.OtherException;
import com.ujujzk.easyenglish.eeapp.dao.parse.Util;
import com.ujujzk.easyenglish.eeapp.model.Base;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class SqliteLocalCrudDao<Model extends Base> implements CrudDao<Model, String> {

    private String getCreateTableQuery(Model model){

        StringBuilder createTableQuery = new StringBuilder();
        createTableQuery.append("CREATE TABLE IF NOT EXISTS ");
        createTableQuery.append(model.getClass().getSimpleName());
        createTableQuery.append(" ( ");

        Set<Field> fields = Util.getFieldsWithAccessible(model.getClass(), Util.CLASSES);
        Iterator<Field> iterator = fields.iterator();
        while(iterator.hasNext()){
            Field field = iterator.next();
            String colName = field.getName();
            Class classType = field.getType();
            createTableQuery.append(" " + colName + " ");

            if(classType.equals(String.class)){
                createTableQuery.append(" NVARCHAR(1000) ");
            } else if(classType.equals(Integer.class)){
                createTableQuery.append(" INTEGER ");
            } else if(classType.equals(Date.class)){
                createTableQuery.append(" DATETIME ");
            } else {
                throw new RuntimeException("unknown class type");
            }

            if(iterator.hasNext()){
                createTableQuery.append(" , ");
            }
        }
        createTableQuery.append(" ); ");
        return createTableQuery.toString();
    }


    @Override
    public Model create(Model model) {
        try {
            return createThrowException(model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Model update(Model model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model createWithRelations(Model model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model updateWithRelations(Model model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model createThrowException(Model model) throws CannotCreateException, OtherException {
        try {
            SQLiteDatabase database = SqliteHelper.getDb();
            database.execSQL(getCreateTableQuery(model));

            ContentValues values = new ContentValues();
            Set<Field> fields = Util.getFieldsWithAccessible(model.getClass(), Util.CLASSES);
            Iterator<Field> iterator = fields.iterator();
            while(iterator.hasNext()) {
                Field field = iterator.next();

                Class classType = field.getType();
                if(classType.equals(String.class)){
                    String value = (String) field.get(model);

                    //if objet id does not exists then generate it
                    if(value == null && field.getName().equals("objectId")){
                        //generate objectId
                        value = "" + model.hashCode() + new Date().getTime();

                        //check that object Id does not exists in database
                    }

                    values.put(field.getName(), value);
                } else if(classType.equals(Integer.class)){
                    values.put(field.getName(), (Integer) field.get(model));
                } else if(classType.equals(Date.class)){
                    Date date = (Date) field.get(model);
                    if(field.getName().equals("createdAt") && date == null){
                        date = new Date();
                    } else if(field.getName().equals("updatedAt") && date == null){
                        date = new Date();
                    }

                    values.put(field.getName(), date.getTime());
                } else {
                    throw new RuntimeException("unknown class type");
                }

            }

            long newRowId = database.insert(model.getClass().getSimpleName(), null, values);
            Log.d(getClass().getName(), "newRowId: " + newRowId);

            //здесь надо создать новый обновленный объект и вернуть пользователю

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model updateThrowException(Model model) throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model createWithRelationsThrowException(Model model) throws CannotCreateException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model updateWithRelationsThrowException(Model model) throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readAllThrowException() throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readAllWithRelations() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readAllWithRelationsThrowException() throws OtherException, DataNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readBy(KeyValue... keyValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readByThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readByWithRelations(KeyValue... keyValues) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Model> readByWithRelationsThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Base getParent(Class parentClazz, Model model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Base getParentThrowException(Class parentClazz, Model model) throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Base getParentWithRelations(Class parentClazz, Model model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Base getParentWithRelationsThrowException(Class parentClazz, Model model) throws OtherException, DataNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteWithRelationsThrowException(java.lang.String string) throws DataNotFoundException, OtherException {
        return false;
    }

    @Override
    public Model readWithRelationsThrowException(java.lang.String string) throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteThrowException(java.lang.String string) throws DataNotFoundException, OtherException {
        return false;
    }

    @Override
    public Model readThrowException(java.lang.String string) throws DataNotFoundException, OtherException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteWithRelations(java.lang.String string) {
        return false;
    }

    @Override
    public Model readWithRelations(java.lang.String string) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(java.lang.String string) {
        return false;
    }

    @Override
    public Model read(java.lang.String string) {
        throw new UnsupportedOperationException();
    }
}
