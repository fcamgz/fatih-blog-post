package com.fatihblog.api.service;

import com.fatihblog.api.model.Post;
import com.fatihblog.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    private final MongoOperations mongoOperations;

    @Autowired
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final CommentService commentService;

    public PostService(MongoOperations mongoOperations, SequenceGeneratorService sequenceGeneratorService, UserService userService, CommentService commentService) {
        this.mongoOperations = mongoOperations;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.userService = userService;
        this.commentService = commentService;
    }

    // TODO create method for common queries

    public List<Post> getAllPosts(int pageNumber, int itemSize, String sortField, String sortDirection) {
        Query query = new Query().with(Sort.by(Objects.equals(sortDirection, "ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,  sortField)).skip((pageNumber -1) * itemSize).limit(itemSize);
        return mongoOperations.find(query, Post.class);
    }

    public int getNumberOfPosts() {
        return mongoOperations.findAll(Post.class).size();
    }

    public List<Post> getTextPosts() {
        Query query = new Query().addCriteria(Criteria.where("postType").is("text"));
        return mongoOperations.find(query, Post.class);
    }

    public List<Post> getVideoPosts() {
        Query query = new Query().addCriteria(Criteria.where("postType").is("video"));
        return mongoOperations.find(query, Post.class);
    }

    public Post getPostById(Long id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findOne(query, Post.class);
    }

    public List<Post> getPostsByUsername(String username) {
        Query query = new Query().addCriteria(Criteria.where("writtenBy").is(username));
        return mongoOperations.find(query, Post.class);
    }

    public Post createPost(@RequestBody Post post) {
        Post postBuilder;

        postBuilder = Post.builder()
                .id(sequenceGeneratorService.generateSequence(Post.SEQUENCE_NAME))
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .isFeatured(post.getIsFeatured())
                .image(post.getImage() != null ? post.getImage() : "https://picsum.photos/1920/1080")
                .writtenBy(post.getWrittenBy())
                .date(LocalDateTime.now())
                .postType(post.getPostType())
                .build();

        // TODO add save method for user posts here

        return mongoOperations.save(postBuilder, "posts");
    }

    public Post editPost(Post post, Long id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("title", post.getTitle());
        update.set("description", post.getDescription());
        update.set("isFeatured", post.getIsFeatured());
        update.set("content", post.getContent());
        update.set("image", post.getImage() != null ? post.getImage() : "https://picsum.photos/1920/1080");
        update.set("lastModified", LocalDateTime.now());
        return mongoOperations.findAndModify(query, update, Post.class);
    }

    public void deletePost (Long id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        commentService.deleteCommentsFromPost(String.valueOf(id));
        mongoOperations.findAndRemove(query, Post.class);
    }
}

