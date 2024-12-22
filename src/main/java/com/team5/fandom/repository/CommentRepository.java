package com.team5.fandom.repository;

import java.util.List;

import com.team5.fandom.entity.Fandom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team5.fandom.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    @Query("SELECT c FROM Comment c WHERE c.post.postId = :postId")
    List<Comment> findByPostId(@Param("postId") Integer postId);

    List<Comment> findAllByOrderByCommentIdDesc();

    List<Comment> findAllByOrderByCommentIdAsc();

    List<Comment> findCommentByCommentContentBefore();

}
