package by.prostrmk.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue
    private Long id;
    private Long postId;
    private Long userId;

    public Like() {
    }

    public Like(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(postId, like.postId) &&
                Objects.equals(userId, like.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }
}
