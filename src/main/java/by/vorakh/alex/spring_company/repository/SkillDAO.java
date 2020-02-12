package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.Skill;

@Repository
public class SkillDAO implements DAO < Skill > {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Skill> getAll() {
        return entityManager.createQuery("select s from Skill s")
                .getResultList();
    }

    @Override
    public Skill getById(int id) {
        return entityManager.find(Skill.class, id);
    }

    @Override
    public int create(Skill entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(Skill entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Skill entity) {
        entityManager.remove(entity);
    }

    @Override
    public boolean isContained(Skill entity) {
        return findExisted(entity) != null;
    }

    public boolean isContained(String skillName) {
        return findExisted(skillName) != null;
    }

    @Override
    public Skill createAndGet(Skill entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Skill findExisted(Skill entity) {
        Query query = entityManager.createQuery("select s from Skill s " +
                "WHERE s.skillName = :p");
        query.setParameter("p", entity.getName());
        return (Skill) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public Skill findExisted(String skillName) {
        Query query = entityManager.createQuery("select s from Skill s " +
                "WHERE s.skillName = :p");
        query.setParameter("p", skillName);
        return (Skill) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }
}
