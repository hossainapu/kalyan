package com.example.polls.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class BaseRepository {

    @PersistenceContext
    private EntityManager em;

    public <T> T findById(Class<T> entity,Integer id) {
        return em.find(entity,id);
    }

    public <T> T findSingleByField(Class<T> entity,String fieldName,Object value) {
        Query query = em.createQuery("select o from "+entity.getSimpleName()+" o where o."+fieldName+" =:field ");
        query.setParameter("field",value);
        query.setFirstResult(0).setMaxResults(1);
        List<T> list = query.getResultList();
        if(list != null && list.size() >0) {
            return list.get(0);
        }

        return null;
    }

    public <T> List<T> findByNativeuery(String sql, Map<String,Object> param) {
        Query query = em.createNativeQuery(sql);
        if(param != null && param.size() >0) {
            param.forEach((k,v)->{
                query.setParameter(k,v);
            });
        }
        return query.getResultList();
    }
}
