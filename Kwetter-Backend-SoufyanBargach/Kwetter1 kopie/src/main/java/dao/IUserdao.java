package dao;


import domain.User;

import java.util.List;

public interface IUserdao {

    User findById(long id);

    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findAll();

    User create(User entity);

    User update(User entity);

    void delete(User entity) ;

    void deleteById(long id) ;
}
