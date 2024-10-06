package com.fatihblog.api.feign;

import com.fatihblog.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userClient", url = "http://localhost:8081")
public interface UserClient {
    @PostMapping("/create")
    User createUser(@RequestBody User user);

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id);

    @GetMapping("/{username}")
    User getUserByUsername(@PathVariable String username);
}
