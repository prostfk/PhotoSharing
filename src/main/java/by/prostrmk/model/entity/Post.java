package by.prostrmk.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String description;

    @Column
    private String path;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Like> likes;

    public Post() {
        likes = new ArrayList<>();
    }

    public Post(String username, String description, String path, List<Like> likes) {
        this.username = username;
        this.description = description;
        this.path = path;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void addLike(Like like){
        likes.add(like);
    }

    public void removeLike(Like like){
        likes.remove(like);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(username, post.username) &&
                Objects.equals(description, post.description) &&
                Objects.equals(path, post.path) &&
                Objects.equals(likes, post.likes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, description, path, likes);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", likes=" + likes +
                '}';
    }
}
