package dao.collection;

import dao.IUserdao;
import domain.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserdaoColImpl implements IUserdao{

    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public User findById(long id) {
        for(User user : users) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for(User user : users) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User create(User entity)  {
        users.add(entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        for(User user : users) {
            if(user.getId().equals(entity.getId())) {


                user.setUsername(entity.getUsername());
                user.setPassword(entity.getPassword());
                user.setBio(entity.getBio());
                user.setLocation(entity.getLocation());

                user.setFollowers(entity.getFollowers());
                user.setFollowing(entity.getFollowing());
                return user;
            }
        }
        return null;
    }



    @Override
    public void delete(User entity) {
        users.remove(entity);
    }

    @Override
    public void deleteById(long id)  {
        for(User user : users) {
            if(user.getId().equals(id)) {
                users.remove(user);
                return;
            }
        }

    }

}
