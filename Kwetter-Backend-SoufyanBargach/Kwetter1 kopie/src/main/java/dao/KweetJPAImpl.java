package dao;

import domain.Kweet;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@JPA
public class KweetJPAImpl extends daoFacade<Kweet> implements IKweetdao  {

    @PersistenceContext(unitName ="KwetterPU")
    private EntityManager entityManager;

    public KweetJPAImpl()
    {
        super(Kweet.class);
    }

    public KweetJPAImpl(EntityManager entityManager) {
        super(Kweet.class);
        this.entityManager = entityManager;

    }

    @Override
    public Kweet create(Kweet entity) {
        if(!entity.getMessage().isEmpty()) {
            if(entity.getMessage().length() < 141 && entity.getMessage().length() > 0) {
                entityManager.persist(entity);
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<Kweet> findByMessage(String message) {
        Query query = entityManager.createQuery(
                "SELECT t FROM Kweet t WHERE t.message = :message")
                .setParameter("message", message);
        return query.getResultList();
    }

    @Override
    public List<Kweet> findByUser(long id) {
        Query query = entityManager.createQuery(
                "SELECT t FROM Kweet t WHERE t.user.id = :id")
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Kweet> findForUser(User user) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        final Kweet entity = findById(id);
        delete(entity);
    }

    @Override
    public List<Kweet> findAll() {
        Query query = entityManager.createQuery(
                "SELECT t FROM Kweet t ");
        return query.getResultList();
    }
}
