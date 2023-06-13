package training.uservideotagdemo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Video> videos = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public User() {

    }

    public User(String name, LocalDate registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public void addVideo(Video video) {
        videos.add(video);
        video.setUser(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", videos=" + videos +
                ", comments=" + comments +
                '}';
    }
}
