package com.team5.fandom.repository;

import com.team5.fandom.entity.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Override
    List<Post> findAllById(Iterable<Integer> iterable);

    @Override
    Page<Post> findAll(Pageable pageable);

    @Override
    <S extends Post> Optional<S> findOne(Example<S> example);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);
}
