package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostReq {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private User user;
    private Fandom fandom;
    private Tag tag;
    private MultipartFile image;

}
