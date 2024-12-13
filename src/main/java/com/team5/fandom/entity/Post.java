package com.team5.fandom.entity;

import com.team5.fandom.entity.value.Tag;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fandom_id")
    private Fandom fandom;

    @Enumerated(EnumType.STRING)
    private Tag tag;
    private String img;


    private Post(String postTitle, String postContent, User user, Fandom fandom, Tag tag, String img) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.user = user;
        this.fandom = fandom;
        this.tag = tag;
        this.img = img;
    }


    //No Id
    public static Post of(String postTitle, String postContent, User user, Fandom fandom, Tag tag, String img) {
        return new Post(postTitle, postContent, user, fandom, tag, img);
    }

}
