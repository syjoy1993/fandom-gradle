package com.team5.fandom.service;

import java.util.List;
import java.util.stream.Collectors;

import com.team5.fandom.entity.Artist;
import org.springframework.stereotype.Service;

import com.team5.fandom.dto.ArtistDto;
import com.team5.fandom.repository.ArtistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDto> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistDto::toArtistDto)
                .collect(Collectors.toList());
    }

    public ArtistDto getArtistById(Integer id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND : " + id));
        return ArtistDto.toArtistDto(artist);
    }
}
