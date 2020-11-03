package com.codegym.repository.impl;

import com.codegym.model.Blog;
import com.codegym.repository.BlogerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogerRepositoryIplm implements BlogerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select c from Blog c", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(long id) {
        TypedQuery<Blog> query = em.createQuery("select c from Blog c where c.id=:id", Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog model) {
        if (model.getId() != null) {
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(long id) {
        Blog blog = findById(id);
        if (blog != null) {
            em.remove(blog);
        }
    }
}