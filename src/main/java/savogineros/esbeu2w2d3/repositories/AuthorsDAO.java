package savogineros.esbeu2w2d3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.esbeu2w2d3.entities.Author;

import java.util.UUID;
@Repository
public interface AuthorsDAO extends JpaRepository<Author, UUID> {

}
