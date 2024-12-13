package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentReqEdit {
    @NotNull
    private Integer postId;
    private String commentContent;




}
