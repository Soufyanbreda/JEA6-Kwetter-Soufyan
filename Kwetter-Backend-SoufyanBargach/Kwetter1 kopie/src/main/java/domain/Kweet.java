package domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@XmlRootElement
public class Kweet implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 140, nullable = false)
    private String message;

    @ManyToOne
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private List<String> hashtags = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    private List<String> mentions = new ArrayList<>();

    public Kweet(String message, User user) {
        Matcher hashtagMatches = Pattern.compile("\\B#\\w\\w+").matcher(message);
        while (hashtagMatches.find()) {
            hashtags.add(hashtagMatches.group());
        }
        this.setHashtags(hashtags);

        Matcher mentionMatches = Pattern.compile("\\B@\\w\\w+").matcher(message);
        while (mentionMatches.find()) {
            mentions.add(mentionMatches.group());
        }
        this.setMentions(mentions);

        this.message = message;
        this.user = user;
    }

    public Kweet() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }
}
