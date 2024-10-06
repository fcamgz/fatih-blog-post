package com.fatihblog.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Transient
    public static final String SEQUENCE_NAME  = "users_sequence";

    @Id
    private Long id;
    private String username;
    private String password;
    private String bio;
    private String image;
}