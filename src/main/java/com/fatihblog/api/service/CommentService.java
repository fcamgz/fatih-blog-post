package com.fatihblog.api.service;

import com.fatihblog.api.feign.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentClient commentClient;

    public CommentService(CommentClient commentClient) {
        this.commentClient = commentClient;
    }

    public void deleteCommentsFromPost(String postId) {
        commentClient.deleteCommentsFromPost(postId);
    }
}
