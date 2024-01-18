package savogineros.esbeu2w2d3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String name;

    private String surname;

    private String email;

    private String dateOfBirth;

    private String avatar;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE) // così quando elimino un author si eliminano anche tutti i blogpost associati
    //@JsonIgnore
    private List<Blogpost> BlogpostList;

    public Author(String name, String surname, String email, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = "https://ui-avatars.com/api/?name="+ name + "+" + surname;
    }
}
