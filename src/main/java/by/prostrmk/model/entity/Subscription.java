package by.prostrmk.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User userWhoSubscribed;
    @OneToOne
    private User subscriptionUser;

    public Subscription() {
    }

    public Subscription(Long userId, Long subId){
        userWhoSubscribed = new User(userId);
        subscriptionUser = new User(subId);
    }

    public Subscription(User userWhoSubscribed, User subscriptionUser) {
        this.userWhoSubscribed = userWhoSubscribed;
        this.subscriptionUser = subscriptionUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserWhoSubscribed() {
        return userWhoSubscribed;
    }

    public void setUserWhoSubscribed(User userWhoSubscribed) {
        this.userWhoSubscribed = userWhoSubscribed;
    }

    public User getSubscriptionUser() {
        return subscriptionUser;
    }

    public void setSubscriptionUser(User subscriptionUser) {
        this.subscriptionUser = subscriptionUser;
    }
}
