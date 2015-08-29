package com.ujujzk.easyenglish.eeapp.dao.parse;

import android.util.Log;
import com.ujujzk.easyenglish.eeapp.dao.exception.CannotCreateException;
import com.ujujzk.easyenglish.eeapp.dao.exception.DataNotFoundException;
import com.ujujzk.easyenglish.eeapp.dao.exception.OtherException;
import com.ujujzk.easyenglish.eeapp.dao.CrudDao;
import com.ujujzk.easyenglish.eeapp.dao.KeyValue;
import com.ujujzk.easyenglish.eeapp.model.Base;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class AbstractParseCrudDaoImpl<Model extends Base> implements CrudDao<Model, String> {

    private final boolean isCloudStorage;
    private final Class<Model> clazz;

    public AbstractParseCrudDaoImpl(boolean isCloudStorage, Class<Model> clazz) {
        this.isCloudStorage = isCloudStorage;
        this.clazz = clazz;
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
    public Model read(String s) {
        try {
            return readThrowException(s);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Model update(Model model) {
        try {
            return updateThrowException(model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public boolean delete(String s) {
        try {
            return deleteThrowException(s);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return false;
    }

    @Override
    public Model createWithRelations(Model model) {
        try {
            return createWithRelationsThrowException(model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Model readWithRelations(String s) {
        try {
            return readWithRelationsThrowException(s);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Model updateWithRelations(Model model) {
        try {
            return updateWithRelationsThrowException(model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public boolean deleteWithRelations(String s) {
        try {
            return deleteWithRelationsThrowException(s);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model createThrowException(Model model) throws CannotCreateException, OtherException {
        Class clazz = model.getClass();
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === CREATE " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + model);

            ParseObject po = Util.createPO(clazz);
            Util.setModel2PO(model, po, isCloudStorage);
            Util.savePO(po, isCloudStorage);
            Util.setPO2Model(po, model, isCloudStorage);
            return model;

        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (ParseException e) {
            throw new CannotCreateException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model readThrowException(String s) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + s);

            ParseObject po = Util.getPO(clazz, s, isCloudStorage);
            Base base = Util.createModel(clazz);
            Util.setPO2Model(po, base, isCloudStorage);
            return (Model) base;

        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model updateThrowException(Model model) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === UPDATE " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + model);

            ParseObject po = Util.getPO(clazz, model.getObjectId(), isCloudStorage);

            Util.setModel2PO(model, po, isCloudStorage);
            Util.savePO(po, isCloudStorage);
            Util.setPO2Model(po, model, isCloudStorage);

            return model;

        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public boolean deleteThrowException(String s) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === DELETE " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + s);

            ParseObject po = Util.getPO(clazz, s, isCloudStorage);
            Util.deletePO(po, isCloudStorage);

            return true;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }



    @Override
    public Model createWithRelationsThrowException(Model model) throws CannotCreateException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === CREATE WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + model);

            Map<Integer, List<Util.ModelPONode>> map =
                    Util.getModelPoTreeRec(clazz, 1, model, null, true, isCloudStorage);
            Util.createRelations(map);
            Util.save(map, isCloudStorage);
            Util.setPO2Model(map, isCloudStorage);

            return model;
        } catch (ParseException e) {
            throw new CannotCreateException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (DataNotFoundException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model readWithRelationsThrowException(String s) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + s);

            ParseObject po = Util.getPO(clazz, s, isCloudStorage);
            Map<Integer, List<Util.ModelPONode>> map =
                    Util.getModelPoTreeRec(clazz, 1, null, po, true, isCloudStorage);
            Util.createRelations(map);
            Util.setPO2Model(map, isCloudStorage);

            return (Model) map.get(1).get(0).modelPO.base;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException();
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public Model updateWithRelationsThrowException(Model model) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === UPDATE WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + model);

            ParseObject po = Util.getPO(clazz, model.getObjectId(), isCloudStorage);

            Map<Integer, List<Util.ModelPONode>> mapNeed =
                    Util.getModelPoTreeRec(clazz, 1, model, null, false, isCloudStorage);
            Map<Integer, List<Util.ModelPONode>> mapExists =
                    Util.getModelPoTreeRec(clazz, 1, null, po, false, isCloudStorage);

            //найти разницу между mapExists - mapNeed = diffExistsNeed
            List<Util.ModelPO> diff = Util.diff(mapExists, mapNeed);

            //diffExistsNeed удалить
            Util.delete(diff, isCloudStorage);

            //mapNeed сохранить
            Util.createRelations(mapNeed);
            Util.save(mapNeed, isCloudStorage);
            Util.setPO2Model(mapNeed, isCloudStorage);

            return model;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public boolean deleteWithRelationsThrowException(String s) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === DELETE WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + s);

            ParseObject po = Util.getPO(clazz, s, isCloudStorage);
            Map<Integer, List<Util.ModelPONode>> map =
                    Util.getModelPoTreeRec(clazz, 1, null, po, true, isCloudStorage);
            Util.createRelations(map);
            Util.delete(map, isCloudStorage);

            return true;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException();
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> readAllWithRelationsThrowException() throws OtherException, DataNotFoundException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ ALL WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);

            List<Base> bases = new ArrayList<Base>();
            List<ParseObject> pos = Util.getPOs(clazz, isCloudStorage);

            for(ParseObject po : pos) {
                Map<Integer, List<Util.ModelPONode>> map =
                        Util.getModelPoTreeRec(clazz, 1, null, po, true, isCloudStorage);
                Util.createRelations(map);
                Util.setPO2Model(map, isCloudStorage);
                bases.add(map.get(1).get(0).modelPO.base);
            }

            return (List<Model>) bases;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException();
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public List<Model> readAllWithRelations() {
        try {
            return readAllWithRelationsThrowException();
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> readAllThrowException() throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ ALL " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);

            List<Base> bases = new ArrayList<Base>();
            List<ParseObject> pos = Util.getPOs(clazz, isCloudStorage);
            for(ParseObject po : pos) {
                Base base = Util.createModel(clazz);
                Util.setPO2Model(po, base, isCloudStorage);
                bases.add(base);
            }

            return (List<Model>) bases;

        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public List<Model> readAll() {
        try {
            return readAllThrowException();
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }


    @Override
    public List<Model> readBy(KeyValue... keyValues) {
        try {
            return readByThrowException(keyValues);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> readByThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ BY " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + Arrays.asList(keyValues));

            List<Base> bases = new ArrayList<Base>();
            List<ParseObject> pos = Util.getPOsBy(clazz, isCloudStorage, keyValues);
            for(ParseObject po : pos) {
                Base base = Util.createModel(clazz);
                Util.setPO2Model(po, base, isCloudStorage);
                bases.add(base);
            }

            return (List<Model>) bases;

        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public List<Model> readByWithRelations(KeyValue... keyValues) {
        try {
            return readByWithRelationsThrowException(keyValues);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> readByWithRelationsThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === READ BY WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "INPUT: " + Arrays.asList(keyValues));

            List<Base> bases = new ArrayList<Base>();
            List<ParseObject> pos = Util.getPOsBy(clazz, isCloudStorage, keyValues);

            for(ParseObject po : pos) {
                Map<Integer, List<Util.ModelPONode>> map =
                        Util.getModelPoTreeRec(clazz, 1, null, po, true, isCloudStorage);
                Util.createRelations(map);
                Util.setPO2Model(map, isCloudStorage);
                bases.add(map.get(1).get(0).modelPO.base);
            }

            return (List<Model>) bases;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException();
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public Base getParent(Class parentClazz, Model model) {
        try {
            return getParentThrowException(parentClazz, model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Base getParentThrowException(Class parentClazz, Model model) throws DataNotFoundException, OtherException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === GET PARENT " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "PARENT CLASS NAME: " + parentClazz.getSimpleName());
            Log.d(getClass().getName(), "INPUT: " + model);

            ParseObject po = Util.getPO(clazz, model.getObjectId(), isCloudStorage);
            ParseObject parentPo = po.getParseObject(parentClazz.getSimpleName());
            if(!parentPo.isDataAvailable()) {
                if(isCloudStorage) {
                    parentPo.fetch();
                } else {
                    parentPo.fetchFromLocalDatastore();
                }
            }
            Base base = Util.createModel(parentClazz);
            Util.setPO2Model(parentPo, base, isCloudStorage);
            return base;

        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException(e.getMessage(), e);
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (OtherException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }

    @Override
    public Base getParentWithRelations(Class parentClazz, Model model) {
        try {
            return getParentWithRelationsThrowException(parentClazz, model);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage() != null ? e.getMessage() : e.toString());
        }
        return null;
    }

    @Override
    public Base getParentWithRelationsThrowException(Class parentClazz, Model model) throws OtherException, DataNotFoundException {
        String className = clazz.getSimpleName();
        try {
            Log.d(getClass().getName(), " --- === GET PARENT WITH RELATIONS " + (isCloudStorage ? "" : "LOCAL") + " === ----");
            Log.d(getClass().getName(), "CLASS NAME: " + className);
            Log.d(getClass().getName(), "PARENT CLASS NAME: " + parentClazz.getSimpleName());
            Log.d(getClass().getName(), "INPUT: " + model);

            ParseObject po = Util.getPO(clazz, model.getObjectId(), isCloudStorage);
            ParseObject parentPo = po.getParseObject(parentClazz.getSimpleName());
            if(!parentPo.isDataAvailable()) {
                if(isCloudStorage) {
                    parentPo.fetch();
                } else {
                    parentPo.fetchFromLocalDatastore();
                }
            }
            Map<Integer, List<Util.ModelPONode>> map = Util.getModelPoTreeRec(parentClazz, 1, null, parentPo, true, isCloudStorage);
            Util.createRelations(map);
            Util.setPO2Model(map, isCloudStorage);

            return map.get(1).get(0).modelPO.base;
        } catch (ParseException e) {
            if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
                throw new DataNotFoundException();
            }
            throw new OtherException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new OtherException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new OtherException(e.getMessage(), e);
        } finally {
            Log.d(getClass().getName(), " ============================================ ");
        }
    }
}
