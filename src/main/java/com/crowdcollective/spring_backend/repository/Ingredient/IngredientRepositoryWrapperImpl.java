package com.crowdcollective.spring_backend.repository.Ingredient;

import java.util.List;

import com.crowdcollective.spring_backend.dao.Ingredient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class IngredientRepositoryWrapperImpl implements IngredientRepositoryWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void delete(Integer id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Ingredient.class);

        Root<Ingredient> ingredient = q.from(Ingredient.class);
        q.where(cb.equal(ingredient.get("ingredientId"), id));
        
        em.createQuery(q).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete q = cb.createCriteriaDelete(Ingredient.class);

        Root<Ingredient> ingredient = q.from(Ingredient.class);
        q.where(ingredient.get("ingredientId").in(ids));
        
        em.createQuery(q).executeUpdate();
    }
}
