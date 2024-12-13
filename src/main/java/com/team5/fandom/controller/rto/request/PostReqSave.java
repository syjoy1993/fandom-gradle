package com.team5.fandom.controller.rto.request;

import com.team5.fandom.dto.PostDto;
import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
@AllArgsConstructor
public class PostReqSave {
    @NotBlank(message = "{post.title.not_blank}")//(message = "제목은 꼭 적어주세요~💕")
    private String postTitle;

    @NotBlank(message = "{post.content.not_blank}")//(message = "한글자 이상은 필수에요~💕")
    private String postContent;
    @NotNull(message = "{post.content.not_null}")//(message = "태그를 꼭 선택해주세요~")
    private Tag tag;

    private MultipartFile image;
}



