package com.crowdcollective.spring_backend.repository.instruction;

import java.util.List;

import com.crowdcollective.spring_backend.dao.Instruction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class InstructionRepositoryWrapperImpl implements InstructionRepositoryWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Instruction.class);

        Root<Instruction> ingredient = q.from(Instruction.class);
        q.where(cb.equal(ingredient.get("instructionid"), id));
        
        em.createQuery(q).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Instruction.class);

        Root<Instruction> instruction = q.from(Instruction.class);
        q.where(instruction.get("instructionId").in(ids));
        
        em.createQuery(q).executeUpdate();
    }
}
