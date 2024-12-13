package com.team5.fandom.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;


    private Comment(String commentContent, User user, Post post) {
        this.commentContent = commentContent;
        this.user = user;
        this.post = post;
    }
    

    // No ID
    public static Comment of(String commentContent, User user, Post post) {
        return new Comment(commentContent, user, post);
    }

}
