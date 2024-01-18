package savogineros.esbeu2w2d3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Blogpost {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String category;

    private String title;

    private String cover;

    private String content;

    private double readingTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    //@JsonIgnore
    private Author author;

    public Blogpost(String category, String title, String content, double readingTime) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
    }
}
