package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiBlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/api/blogs/", method = RequestMethod.GET)
    public ResponseEntity<Page<Blog>> listAllBlogs(Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        if (blogs==null) {
            return new ResponseEntity<Page<Blog>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Page<Blog>> (blogs, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/blogs/{id}")
    public ResponseEntity<Blog> delete(@PathVariable("id") long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.delete(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/blogs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> getBlogById(@PathVariable("id") long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PutMapping(value = "/api/blogs/")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blogUpdate) {
        if (blogUpdate == null) {
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        blogService.save(blogUpdate);
        return new ResponseEntity<Blog>(blogUpdate, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/blogs/create",produces = MediaType.APPLICATION_JSON_VALUE,method =RequestMethod.POST)
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }

    //    @RequestMapping(value = "/api/blogs/search", method = RequestMethod.GET)
//    public ResponseEntity<Page<Blog>> listBlogsByName(@RequestParam("name") String name, Pageable pageable) {
//        Page<Blog> blogs = blogService.findAllByNameContaining(name,pageable);
//        if (blogs.isEmpty()) {
//            return new ResponseEntity<Page<Blog>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
//    }

}
