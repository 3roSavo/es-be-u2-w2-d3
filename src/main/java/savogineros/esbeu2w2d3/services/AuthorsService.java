package savogineros.esbeu2w2d3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.esbeu2w2d3.entities.Author;
import savogineros.esbeu2w2d3.exceptions.NotFoundException;
import savogineros.esbeu2w2d3.repositories.AuthorsDAO;

import java.util.*;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsDAO authorsDAO;
    //private final List<Author> authors = new ArrayList<>();

    public Author save(Author author) {
        //this.authors.add(author);
        return authorsDAO.save(author); // la save ritorna pure l'oggetto salvato
        //return author;
    }

    // GET all authors
    public Page<Author> getAuthors(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));
        return authorsDAO.findAll(pageable); // il metodo dao findAll torna invece una lista
    }

    public Author findById(UUID id) {
        Optional<Author> author = authorsDAO.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new RuntimeException("Utente con id " + id + " non trovato");
        }

        // OPPURE
        // return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    // DELETE
    public void findByIdAndDelete(UUID id) {
        Author author = this.findById(id);
        authorsDAO.delete(author);
    }

    // PUT
    public Author findByIdAndUpdate(UUID id, Author author) {

        Author author1 = findById(id);
        author1.setName(author.getName());
        author1.setSurname(author.getSurname());
        author1.setEmail(author.getEmail());
        author1.setDateOfBirth(author.getDateOfBirth());
        author1.setAvatar(author.getAvatar());
        return authorsDAO.save(author1);
    }
}
