package com.team5.fandom.controller;

import com.team5.fandom.controller.rto.request.PostReqSave;
import com.team5.fandom.controller.rto.response.PostResponse;
import com.team5.fandom.controller.rto.response.PostTitleResponse;
import com.team5.fandom.dto.PostDto;
import com.team5.fandom.security.FanUserDetails;
import com.team5.fandom.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.sum;
import static java.time.LocalTime.now;


@Controller
@RequestMapping("/f/c/{fandom_id}")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Value("${file.upload.dir}")// 속성사용
    private String uploadDir;


    // 게시판 목록
    @GetMapping("/p")
    public String getPosts(@PageableDefault(size = 7,sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
                            Model model) {

        Page<PostDto> postDtos = postService.getPostList(pageable);


        Page<PostTitleResponse> responsePage = postDtos.map(postDto -> PostTitleResponse.builder()
                .id(postDto.getPostId())
                .title(postDto.getPostTitle())
                .content(postDto.getPostContent())
                .name(postDto.getUserDto().getUserName())
                .tag(postDto.getTag().toString())
                .build()
        );

        model.addAttribute("posts", responsePage);
        model.addAttribute("pageNum", responsePage.getNumber());
        model.addAttribute("hasNext", responsePage.hasNext());
        model.addAttribute("hasPrevious", responsePage.hasPrevious());

        model.addAttribute("pageTotal", responsePage.getTotalPages());

        return "redirect:/f/c/{fandom_id}/p";
    }

    //작성폼
    @GetMapping("/p/new")
    public String newPostForm(Model model, @PathVariable("fandom_id") int fandom_id) {

        model.addAttribute("post", "새 글 작성");
        return "post-form";
    }


    @PostMapping("/p/new")
    public String savePost(
            @AuthenticationPrincipal FanUserDetails userDetails,
            @Validated @ModelAttribute("post") PostReqSave postReqSave,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            return "redirect:/f/c/+fandom_id+/p";
        }
        //크키 체크
        MultipartFile image = postReqSave.getImage();
        if (image != null && !image.isEmpty() && image.getSize() > 6*1024*1024) {
            bindingResult.rejectValue("image","file.size.exceed", "용량 초과");
            return "post-form";

        }

        //MultipartFile -> String
        String imagePath = null;
        if (image != null && !image.isEmpty()) {

            try {

                String currentDatePath = now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss"));
                Path imgDirPath = Paths.get(uploadDir + currentDatePath);//uploadDir 설정
                Files.createDirectories(imgDirPath);

                String unique = UUID.randomUUID() + "-" + image.getOriginalFilename();
                Path fullpath = imgDirPath.resolve(unique);


                image.transferTo(fullpath.toFile());

                imagePath = "/upload/" + currentDatePath + "/" + unique;


            } catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류 발생",e);
            }

        }

        postService.savePost(postReqSave, imagePath, userDetails);
        redirectAttributes.addFlashAttribute("successMessage", "게시글이 성공적으로 등록되었습니다!");

        return "/";


    }
}
