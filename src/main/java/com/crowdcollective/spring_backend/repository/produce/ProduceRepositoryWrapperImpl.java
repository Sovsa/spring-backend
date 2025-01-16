package com.crowdcollective.spring_backend.repository.produce;

import java.util.List;

import com.crowdcollective.spring_backend.dao.Instruction;
import com.crowdcollective.spring_backend.dao.Produce;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class ProduceRepositoryWrapperImpl implements ProduceRepositoryWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Produce.class);

        Root<Produce> produce = q.from(Produce.class);
        q.where(cb.equal(produce.get("produceId"), id));
        
        em.createQuery(q).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(List<Integer> id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Produce.class);

        Root<Produce> produce = q.from(Produce.class);
        q.where(produce.get("produceId").in(id));
        
        em.createQuery(q).executeUpdate();
    }

}
