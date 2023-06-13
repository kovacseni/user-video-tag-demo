package training.uservideotagdemo;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Tag() {
    }

    public Tag(String text) {
        this.text = "#" + text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
