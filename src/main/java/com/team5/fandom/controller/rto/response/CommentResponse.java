package com.team5.fandom.controller.rto.response;


import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private Integer commentId;
    private String commentContent;

    private User user;

    private Post post;
}
