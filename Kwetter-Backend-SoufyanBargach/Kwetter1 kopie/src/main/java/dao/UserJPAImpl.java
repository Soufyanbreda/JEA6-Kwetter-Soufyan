package dao;

import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@JPA

public class UserJPAImpl extends daoFacade<User> implements IUserdao{

    @PersistenceContext(unitName ="KwetterPU")
    private EntityManager entityManager;

    public UserJPAImpl() {
        super(User.class);

    }

    public UserJPAImpl(EntityManager entityManager) {
        super(User.class);
        this.entityManager = entityManager;
    }

    @Override
    public User create(User entity)  {
        entityManager.persist(entity);
        return entity;
    }

    public User update(User entity)  {
        return entityManager.merge(entity);
    }

    @Override
    public User updateRole(User entity)  {
        return entityManager.merge(entity);
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username")
                .setParameter("username", username);

        if(query.getResultList().isEmpty()) {
            return null;
        } else {
            return (User) query.getSingleResult();
        }
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email);

        if(query.getResultList().isEmpty()) {
            return null;
        } else {
            return (User) query.getSingleResult();
        }
    }

    @Override
    public void deleteById(long id) {
        final User entity = findById(id);
        delete(entity);
    }

    @Override
    public void delete(User entity) {

    }

}
