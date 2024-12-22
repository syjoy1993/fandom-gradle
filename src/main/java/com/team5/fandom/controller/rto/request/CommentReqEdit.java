package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentReqEdit {
    @NotNull
    private Integer postId;
    private String commentContent;




}
