package com.homebrew.persistance.dao;

import com.homebrew.exception.BaseException;
import com.homebrew.persistance.criterion.ICriterion;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;


public interface IAbstractDao<C, T> {

    Criteria getCriteria();

    C read(Serializable id)
            throws BaseException;

    C get(ICriterion criterion)
            throws BaseException;

    C get(Criteria criteria)
            throws BaseException;

    C refresh(C object)
            throws BaseException;

    C merge(C object)
            throws BaseException;

    Boolean check(Criteria criteria)
            throws BaseException;

    T create(C object)
            throws BaseException;

    void update(C object)
            throws BaseException;

    void delete(C object)
            throws BaseException;

    List<C> search(Integer limitOffset, Integer limitCount, ICriterion criterion)
            throws BaseException;

    List<C> search(Integer limitOffset, Integer limitCount, Criteria criteria)
            throws BaseException;

    Integer searchCount(ICriterion criterion)
            throws BaseException;

    Integer searchCount(Criteria criteria)
            throws BaseException;

}
