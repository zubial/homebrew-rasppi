package com.homebrew.persistance.dao.impl;

import com.homebrew.exception.BaseException;
import com.homebrew.exception.PersistanceException;
import com.homebrew.persistance.criterion.ICriterion;
import com.homebrew.persistance.dao.IAbstractDao;
import com.homebrew.persistance.helper.CriteriaHelper;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImpl<C, T> implements IAbstractDao<C, T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);

    private final Class<C> typeClass;
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    
    @Value("${ds.default_schema}")
    private String jdbcSchema;

    public AbstractDaoImpl(final Class<C> typeClass) {
        this.typeClass = typeClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Criteria getCriteria() {
        return getSession().createCriteria(typeClass);
    }

    @Override
    @Transactional(readOnly = true)
    public C read(final Serializable id)
            throws BaseException {
        try {
            return getSession().get(typeClass, id);

        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public C refresh(final C entity)
            throws BaseException {
        try {
            getSession().refresh(entity);
            return entity;

        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
	@Transactional(rollbackFor = { BaseException.class })
    public C merge(final C entity)
            throws BaseException {
        try {
            final Session session = getSession();
            final C object = (C) session.merge(entity);
            session.flush();
            session.refresh(entity);
            return object;
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public C get(final ICriterion criterion)
            throws BaseException {
        CriteriaHelper criteriaHelper = new CriteriaHelper(getCriteria());
        criterion.addCriterion(criteriaHelper);

        return get(criteriaHelper.getCriteria());
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public C get(final Criteria criteria)
            throws BaseException {
        try {
            addSearchOrder(criteria);
            return (C) criteria.setMaxResults(1).uniqueResult();
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean check(final Criteria criteria)
            throws BaseException {
        try {
            criteria.setProjection(Projections.rowCount());

            return ((Long) criteria.uniqueResult()) == 0L;
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
	@Transactional(rollbackFor = { BaseException.class })
    public T create(final C entity)
            throws BaseException {
        try {
            final Session session = getSession();
            final T id = (T) session.save(entity);
            session.flush();
            session.refresh(entity);
            return id;
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
	@Transactional(rollbackFor = { BaseException.class })
    public void update(final C entity)
            throws BaseException {
        try {
            final Session session = getSession();
            session.clear();

            session.update(entity);
            session.flush();

            getSession().refresh(entity);

        } catch (final HibernateException | DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
	@Transactional(rollbackFor = { BaseException.class })
    public void delete(final C entity)
            throws BaseException {
        try {
            final Session session = getSession();
            session.delete(entity);
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<C> search(final Integer limitOffset, final Integer limitCount, final ICriterion criterion)
            throws BaseException {
        CriteriaHelper criteriaHelper = new CriteriaHelper(getCriteria());
        criterion.addCriterion(criteriaHelper);

        return search(limitOffset, limitCount, criteriaHelper.getCriteria());
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<C> search(final Integer limitOffset, final Integer limitCount, final Criteria criteria)
            throws BaseException {
        try {
            if (limitCount != null) {
                if (limitOffset != null && limitOffset > 0) {
                    criteria.setFirstResult(limitOffset * limitCount);
                } else {
                    criteria.setFirstResult(0);
                }
                if (limitCount > 0) {
                    criteria.setMaxResults(limitCount);
                }
            }

            addSearchOrder(criteria);

            return criteria.list();
        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Integer searchCount(final ICriterion criterion)
            throws BaseException {
        CriteriaHelper criteriaHelper = new CriteriaHelper(getCriteria());
        criterion.addCriterion(criteriaHelper);

        return searchCount(criteriaHelper.getCriteria());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer searchCount(final Criteria criteria)
            throws BaseException {
        try {
            criteria.setProjection(Projections.rowCount());

            return ((Long) criteria.uniqueResult()).intValue();

        } catch (final HibernateException e) {
            LOGGER.error(e.getMessage(), e);
            throw new PersistanceException(e.getMessage(), e);
        }
    }

    public String getJdbcSchema() {
        return jdbcSchema;
    }

	public abstract void addSearchOrder(@SuppressWarnings("unused") final Criteria criteria);
}
