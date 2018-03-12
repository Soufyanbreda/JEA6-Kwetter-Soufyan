package service;

import dao.IKweetdao;
import dao.IUserdao;
import dao.JPA;
import domain.Kweet;
import domain.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

public class TweetService {

    @Inject
    @JPA
    private IKweetdao tweetDao;

    @Inject
    @JPA
    private IUserdao userDao;

    public TweetService() {
        super();
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public Kweet create(Kweet tweet)  {
        User user = userDao.findById(tweet.getUser().getId());
        if(user != null) {
            tweet = tweetDao.create(tweet);
            user.addTweet(tweet);
            userDao.update(user);
            return tweet;
        }
        return null;
    }

    @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void delete(long id) {
        Kweet entity = tweetDao.findById(id);
        tweetDao.delete(entity);
    }

    @PermitAll
    public Kweet findById(long id) {
        return tweetDao.findById(id);
    }

    @PermitAll
    public List<Kweet> findByMessage(String arg) {
        return tweetDao.findByMessage(arg);
    }

    @PermitAll
    public List<Kweet> findByUser(long id) {
        return tweetDao.findByUser(id);
    }

    @PermitAll
    public List<Kweet> findAll() {
        return tweetDao.findAll();
    }
}
