package dao;

import domain.Kweet;
import domain.User;

import java.util.List;

public interface IKweetdao {


    Kweet findById(long id);


    List<Kweet> findByMessage(String message);


    List<Kweet> findByUser(long id);


    List<Kweet> findForUser(User user);


    List<Kweet> findAll();

    Kweet create(Kweet entity);


    void delete(Kweet entity);


    void deleteById(long id);

}
