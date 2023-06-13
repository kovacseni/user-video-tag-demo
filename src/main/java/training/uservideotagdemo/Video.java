package training.uservideotagdemo;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalTime length;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    public Video() {
    }

    public Video(String title, LocalTime length) {
        this.title = title;
        this.length = length;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
        this.length = length;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", tags=" + tags +
                '}';
    }
}
