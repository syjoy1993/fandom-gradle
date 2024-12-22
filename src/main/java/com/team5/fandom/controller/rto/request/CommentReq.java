package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentReq {

    private Integer commentId;
    private String commentContent;

    private User user;

    private Post post;
}
