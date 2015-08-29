package com.ujujzk.easyenglish.eeapp.dao;

import com.ujujzk.easyenglish.eeapp.dao.exception.CannotCreateException;
import com.ujujzk.easyenglish.eeapp.dao.exception.DataNotFoundException;
import com.ujujzk.easyenglish.eeapp.dao.exception.OtherException;
import com.ujujzk.easyenglish.eeapp.model.Base;

import java.util.List;

public interface CrudDao<Model extends Base, PK> {

    public Model create(Model model);
    public Model read(PK pk);
    public Model update(Model model);
    public boolean delete(PK pk);

    public Model createWithRelations(Model model);
    public Model readWithRelations(PK pk);
    public Model updateWithRelations(Model model);
    public boolean deleteWithRelations(PK pk);

    public Model createThrowException(Model model) throws CannotCreateException, OtherException;
    public Model readThrowException(PK pk) throws DataNotFoundException, OtherException;
    public Model updateThrowException(Model model) throws DataNotFoundException, OtherException;
    public boolean deleteThrowException(PK pk) throws DataNotFoundException, OtherException;

    public Model createWithRelationsThrowException(Model model) throws CannotCreateException, OtherException;
    public Model readWithRelationsThrowException(PK pk) throws DataNotFoundException, OtherException;
    public Model updateWithRelationsThrowException(Model model) throws DataNotFoundException, OtherException;
    public boolean deleteWithRelationsThrowException(PK pk) throws DataNotFoundException, OtherException;

    //additions
    public List<Model> readAll();
    public List<Model> readAllThrowException() throws DataNotFoundException, OtherException;
    public List<Model> readAllWithRelations();
    public List<Model> readAllWithRelationsThrowException() throws OtherException, DataNotFoundException;

    public List<Model> readBy(KeyValue... keyValues);
    public List<Model> readByThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException;
    public List<Model> readByWithRelations(KeyValue... keyValues);
    public List<Model> readByWithRelationsThrowException(KeyValue... keyValues) throws OtherException, DataNotFoundException;

    public Base getParent(Class parentClazz, Model model);
    public Base getParentThrowException(Class parentClazz, Model model) throws DataNotFoundException, OtherException;
    public Base getParentWithRelations(Class parentClazz, Model model);
    public Base getParentWithRelationsThrowException(Class parentClazz, Model model) throws OtherException, DataNotFoundException;

}
