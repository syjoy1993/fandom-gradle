package com.team5.fandom.controller.rto.request;

import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.User;
import com.team5.fandom.entity.value.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostReqEdit {
    @NotBlank//(message = "제목은 꼭 적어주세요~💕")
    private String postTitle;

    private String postContent;
    @NotNull//(message = "태그를 꼭 선택해주세요~")
    private Tag tag;

    private MultipartFile photo;

}
