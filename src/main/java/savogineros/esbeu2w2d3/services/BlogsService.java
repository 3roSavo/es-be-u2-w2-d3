package savogineros.esbeu2w2d3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.esbeu2w2d3.entities.Blogpost;
import savogineros.esbeu2w2d3.exceptions.NotFoundException;
import savogineros.esbeu2w2d3.repositories.BlogpostsDao;

import java.util.*;

@Service
public class BlogsService {
    @Autowired
    private BlogpostsDao blogpostsDao;

    // METODI

    // POST
    public Blogpost save(Blogpost blogpost) {
        blogpost.setCover("https://picsum.photos/200/300");
        return blogpostsDao.save(blogpost);  // la save() ritorna l'oggetto salvato
    }
    // GET (all blogs)
    public List<Blogpost> getBlogs() {
        return blogpostsDao.findAll(); // il metodo DAO findAll ritorna una lista
    }

    // GET (one blog)
    public Blogpost findById(UUID id) {
        Optional<Blogpost> blogpost = blogpostsDao.findById(id);
        if (blogpost.isPresent()) {
            return blogpost.get();
        } else {
            throw new RuntimeException("Blogpost con id " + id + " non presente");
            // OPPURE
            //return blogpostsDao.findById(id).orElseThrow(() -> new NotFoundException(id));
        }
    }
    // DELETE
    public void findByIdAndDelete(UUID id) {
        Blogpost blogpost = findById(id);
        blogpostsDao.delete(blogpost);
    }

    public Blogpost findByIdAndUpdate(UUID id, Blogpost body) {
        Blogpost blogpost = findById(id);

        blogpost.setTitle(body.getTitle());
        blogpost.setCategory(body.getCategory());
        blogpost.setContent(body.getContent());
        blogpost.setCover(body.getCover());
        blogpost.setReadingTime(body.getReadingTime());

        return blogpostsDao.save(blogpost);
    }
}
