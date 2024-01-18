package savogineros.esbeu2w2d3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savogineros.esbeu2w2d3.entities.Author;
import savogineros.esbeu2w2d3.entities.Blogpost;
import savogineros.esbeu2w2d3.exceptions.NotFoundException;
import savogineros.esbeu2w2d3.payloads.NewBlogpostPayload;
import savogineros.esbeu2w2d3.repositories.BlogpostsDao;

import java.util.*;

@Service
public class BlogsService {
    @Autowired
    private BlogpostsDao blogpostsDao;
    @Autowired
    AuthorsService authorsService;

    // METODI

    // POST
    public Blogpost save(NewBlogpostPayload blogpost) {
        Author author = authorsService.findById(blogpost.getAuthorId());

        Blogpost newBlogpost = new Blogpost();
        newBlogpost.setAuthor(author);
        newBlogpost.setTitle(blogpost.getTitle());
        newBlogpost.setCategory(blogpost.getCategory());
        newBlogpost.setCover("https://picsum.photos/200/300");
        newBlogpost.setReadingTime(blogpost.getReadingTime());
        newBlogpost.setContent(blogpost.getContent());

        System.out.println(newBlogpost);

        return blogpostsDao.save(newBlogpost);  // la save() ritorna l'oggetto salvato
    }
    // GET (all blogs)
    public Page<Blogpost> getBlogs(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return blogpostsDao.findAll(pageable); // il metodo DAO findAll ritorna una lista
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
