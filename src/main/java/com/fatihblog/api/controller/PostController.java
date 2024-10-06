package com.fatihblog.api.controller;

import com.fatihblog.api.model.Post;
import com.fatihblog.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Value("${test.value}")
    private String testValue;

    @GetMapping("/testValue")
    public ResponseEntity<String> getTestValue() {
        return new ResponseEntity<>(testValue, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int sizePerPage,
                                                  @RequestParam(defaultValue = "_id") String sortField,
                                                  @RequestParam(defaultValue = "DESC") String sortDirection) {
        try {
            List<Post> posts = postService.getAllPosts(page, sizePerPage, sortField, sortDirection);
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getNumberOfPosts() {
        try {
            int count = postService.getNumberOfPosts();
            if (count == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/videos")
    public ResponseEntity<List<Post>> getVideoPosts() {
        try {
            List<Post> posts = postService.getVideoPosts();
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/texts")
    public ResponseEntity<List<Post>> getTextPosts() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-status-set","someValue");
        try {
            List<Post> posts = postService.getTextPosts();
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().headers(headers).body(posts);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        try {
            Post _post = postService.getPostById(id);
            if (_post != null) {
                return new ResponseEntity<>(_post, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByUsername/{username}")
    public ResponseEntity<List<Post>> getPostsByUsername(@PathVariable String username) {
        try {
            List<Post> _posts = postService.getPostsByUsername(username);
            if (_posts != null) {
                return new ResponseEntity<>(_posts, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post _post = postService.createPost(post);
            return new ResponseEntity<>(_post, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long id) {
        try {
            Post _post = postService.editPost(post, id);
            return new ResponseEntity<>(_post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost (@PathVariable Long id) throws ClassNotFoundException{
        try {
            postService.deletePost(id);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }
}