package com.team5.fandom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.fandom.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> { 

}
