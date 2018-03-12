package service;

import dao.IUserdao;
import dao.JPA;
import domain.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

public class UserService {

    @Inject
    @JPA
    private IUserdao dao;

    public UserService()
    {
        super();
    }

    @PermitAll
    public User create(User entity)  {
        if(dao.findByUsername(entity.getUsername()) == null
                && dao.findByEmail(entity.getEmail()) == null) {
            return dao.create(entity);
        }
        return null;
    }

    @RolesAllowed({"ADMINISTRATOR"})
    public void delete(long id) {
        User entity = dao.findById(id);
        dao.delete(entity);
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void update(User entity) {
        User user = dao.findById(entity.getId());
        dao.update(user);
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void updateUsername(String username, User user)  {
        if (dao.findByUsername(username) == null && !username.isEmpty()) {
            user.setUsername(username);
            dao.update(user);
        }
    }

    @RolesAllowed({"ADMINISTRATOR"})
    public void updateRole(String role, User user)  {
        user.setRole(role);
        dao.update(user);
    }

    @PermitAll
    public User findById(long id) {
        return dao.findById(id);
    }

    @PermitAll
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @PermitAll
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void addFollowing(long followingId, long id) {
        if(followingId != id) {
            User user = dao.findById(id);
            User followingUser = dao.findById(followingId);
            if(user != null && followingUser != null) {

                user.addFollowing(followingUser);
                followingUser.addFollower(user);

                dao.update(user);
                dao.update(followingUser);
            }
        }
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void removeFollowing(long followingId, long id) {
        if(followingId != id) {
            User user = dao.findById(id);
            User followingUser = dao.findById(followingId);
            if(user != null && followingUser != null) {
                user.removeFollowing(followingUser);
                followingUser.removeFollower(user);

                dao.update(user);
                dao.update(followingUser);
            }
        }
    }

    @PermitAll
    public List<User> findAll() {
        return dao.findAll();
    }
}
