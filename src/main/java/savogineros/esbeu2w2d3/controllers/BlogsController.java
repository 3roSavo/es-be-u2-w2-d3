package savogineros.esbeu2w2d3.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import savogineros.esbeu2w2d3.entities.Blogpost;
import savogineros.esbeu2w2d3.payloads.NewBlogpostPayload;
import savogineros.esbeu2w2d3.services.BlogsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost saveBlog(@RequestBody NewBlogpostPayload body) {
        return blogsService.save(body);
    }

    // 2. - GET http://localhost:3001/blogs
    @GetMapping("")
    public Page<Blogpost> getBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "category") String sortBy) {
        return blogsService.getBlogs(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/blogs/{blogId}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable UUID blogId) {
        return blogsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{blogId} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable UUID blogId, @RequestBody Blogpost body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{blogId}
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable UUID blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}
