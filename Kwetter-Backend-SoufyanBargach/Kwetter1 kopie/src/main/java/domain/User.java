package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @JsonIgnore
    @Column(nullable = false, length = 20)
    private String password;
    private String location;
    private String websiteUrl;

    @Column(length = 160)
    private String bio;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Kweet> tweets = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    private List<User> following = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "following")
    private List<User> followers = new ArrayList<>();

    public User(String role, String email, String username, String password) {
        this.role = role;
        this.email = email;

        this.username = username;
        this.password = password;

        this.location = "";
        this.websiteUrl = "";
        this.bio = "";
    }

    public User() {}



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getWebsiteUrl() {
        return websiteUrl;
    }


    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }


    public String getBio() {
        return bio;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }


    public List<Kweet> getTweets() {
        return tweets;
    }


    public void addTweet(Kweet tweet) {
        tweets.add(tweet);
    }


    public void setTweets(List<Kweet> tweets) {
        this.tweets = tweets;
    }


    public List<User> getFollowing() {
        return following;
    }


    public void addFollowing(User user) {
        following.add(user);
    }


    public void removeFollowing(User user) {
        following.remove(user);
    }


    public void setFollowing(List<User> following) {
        this.following = following;
    }


    public List<User> getFollowers() {
        return followers;
    }


    public void addFollower(User follower) {
        followers.add(follower);
    }


    public void removeFollower(User follower) {
        followers.remove(follower);
    }


    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
