package com.codetreatise.repository;

import com.codetreatise.common.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class BaseDao {

    @PersistenceContext
    private EntityManager em;

    public <T> List<T> findAll(Class<T> entity) {
        String sql = "select o from "+entity.getSimpleName()+" o where 1=1 ";
        return em.createQuery(sql,entity).getResultList();
    }

    public <T> T persist(T entity) {
        em.persist(entity);
        em.flush();
        return (T)entity;
    }

    public <T> T update(T entity) {
       em.merge(entity);
       em.flush();
       return (T)entity;
    }

    public <T> T findById(Class<T> entity,Integer id) {
        return em.find(entity,id);
    }

    public Integer findCountByNativeQuery(String sql, Map<String,Object> param) {
        if(Utils.isEmpty(sql)) {
            return 0;
        }

        Query q = em.createNativeQuery(sql);
        if(param != null && param.size() >0) {
            param.forEach((k,v)->{
                q.setParameter(k,v);
            });
        }

        return Utils.getInteger(q.getSingleResult());
    }

    public <T> List<T> findByNativeQuery(String sql,Class<T> eClass,Map<String,Object> param,Integer startIndex,Integer limit) {
        if(Utils.isEmpty(sql)) {
            return new ArrayList<>();
        }

        if(startIndex == null) {
            startIndex = 0;
        }

        if(Utils.isNull(limit)) {
            limit =100;
        }

        Query query = em.createNativeQuery(sql,eClass);
        if(param != null && param.size() >0) {
            param.forEach((k,v)->{
                query.setParameter(k,v);
            });
        }

        query.setFirstResult(startIndex);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    public <T> T findSingleByField(Class<T> entity,String field,Object value) {
        String sql = "SELECT o FROM "+entity.getSimpleName()+" o Where o."+field+" =:value ";
        try {
            return em.createQuery(sql, entity).setParameter("value", value).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public int updateByNativeSQL(String sql,Map<String,Object> param) {
        Query query = em.createNativeQuery(sql);
        if(param != null && param.size() >0) {
            param.forEach((k,v)->{
                query.setParameter(k,v);
            });
        }

        return query.executeUpdate();
    }

    public <T> T findSingleResult(String sql,Class<T> entity,Map<String,Object> param) {
        Query query = em.createQuery(sql,entity);
        if(param != null && param.size() >0) {
            param.forEach((k,v)->{
                query.setParameter(k,v);
            });
        }
        query.setFirstResult(0).setMaxResults(1);

        List<T> result = query.getResultList();
        if(result != null && result.size() >0) {
            return result.get(0);
        }

        return null;
    }

}
