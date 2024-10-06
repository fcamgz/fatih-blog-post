package com.fatihblog.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Transient
    public static final String SEQUENCE_NAME  = "posts_sequence";

    @Id
    private Long id;
    private String title;
    private String description;
    private String content;
    private Boolean isFeatured;
    private String writtenBy;
    private String image;
    private LocalDateTime date;
    private LocalDateTime lastModified;
    private String postType;
}
