package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentReqSave {
    @NotNull
    private Integer postId;
    @NotBlank(message = "한글자 이상 꼭 적어주세용")
    private String commentContent;
}
