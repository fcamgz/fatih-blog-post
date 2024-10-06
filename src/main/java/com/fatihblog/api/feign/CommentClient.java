package com.fatihblog.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "commentClient", url = "http://localhost:8082/comments")
public interface CommentClient {
    @DeleteMapping("delete/{postId}")
    void deleteCommentsFromPost(@PathVariable String postId);
}
