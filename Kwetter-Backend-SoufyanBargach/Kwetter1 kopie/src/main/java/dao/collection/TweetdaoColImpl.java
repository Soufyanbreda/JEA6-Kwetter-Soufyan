package dao.collection;

import domain.Kweet;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TweetdaoColImpl {

    private final CopyOnWriteArrayList<Kweet> tweets = new CopyOnWriteArrayList<>();


    public Kweet findById(long id) {
        for(Kweet tweet : tweets) {
            if(tweet.getId().equals(id)) {
                return tweet;
            }
        }
        return null;
    }

    public List<Kweet> findByMessage(String message) {
        List<Kweet> tweetsFound = new ArrayList<>();

        for(Kweet tweet : tweets) {
            if(tweet.getMessage().equals(message)) {
                tweetsFound.add(tweet);
            }
        }
        return tweetsFound;
    }


    public List<Kweet> findByUser(long id) {
        List<Kweet> tweetsFound = new ArrayList<>();

        for(Kweet tweet : tweets) {
            if(tweet.getUser().getId().equals(id)) {
                tweetsFound.add(tweet);
            }
        }
        return tweetsFound;
    }


    public List<Kweet> findForUser(User user) {
        List<Kweet> tweetsFound = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (User follower : user.getFollowing()) {
            ids.add(follower.getId());
        }
        ids.add(user.getId());

        for(Kweet tweet : tweets) {
            if(ids.contains(tweet.getUser().getId())) {
                tweetsFound.add(tweet);
            }
        }

        return tweetsFound;
    }


    public List<Kweet> findAllOrderedByDate() {
        return tweets;
    }


    public Kweet create(Kweet entity) {
        if(!entity.getMessage().isEmpty()) {
            if(entity.getMessage().length() < 141 && entity.getMessage().length() > 0) {
                tweets.add(entity);
                return entity;
            }
        }
        return null;
    }

    public Kweet update(Kweet entity) {
        for(Kweet tweet : tweets) {
            if(tweet.getId().equals(entity.getId())) {
                tweet = entity;
            }
        }
        return null;
    }


    public void delete(Kweet entity) {
        tweets.remove(entity);
    }


    public void deleteById(long id) {
        for(Kweet tweet : tweets) {
            if(tweet.getId().equals(id)) {
                tweets.remove(tweet);
                return;
            }
        }
    }

    public List<Kweet> findAll() {
        return tweets;
    }
}
