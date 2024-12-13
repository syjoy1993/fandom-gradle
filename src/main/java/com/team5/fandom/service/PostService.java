package com.team5.fandom.service;

import com.team5.fandom.controller.rto.request.PostReqSave;
import com.team5.fandom.dto.PostDto;
import com.team5.fandom.entity.Post;
import com.team5.fandom.entity.User;
import com.team5.fandom.repository.PostRepository;
import com.team5.fandom.security.FanUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostDto> getPostList(Pageable pageable) {

        Page<PostDto> postDtos = postRepository.findAll(pageable).map(post -> PostDto.toPostDto(post));
        return postDtos;
    }


    public PostDto savePost(PostReqSave postReqSave, String imagePath, FanUserDetails userDetails) {

        User user = FanUserDetails.of(userDetails);

        Post post = Post.of(postReqSave.getPostTitle(),
                postReqSave.getPostContent(),
                user,
                user.getFandom(),
                postReqSave.getTag(),
                imagePath);


        Post save = postRepository.save(post);
        return PostDto.toPostDto(save);


    }
}
