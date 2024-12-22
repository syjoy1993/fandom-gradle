package com.team5.fandom.dto;

import java.time.LocalDateTime;

import com.team5.fandom.entity.Artist;


import lombok.*;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtistDto {
    private Integer artistId;
    private String artistName;
    private LocalDateTime debutDate;

    // Entity -> Dto 변환 메서드
    public static ArtistDto toArtistDto(Artist artist) {
        return new ArtistDto(
                artist.getArtistId(),
                artist.getArtistName(),
                artist.getDebutDate()
        );
    }

    // Dto -> Entity 변환 메서드
    public Artist toEntity() {
        return Artist.of(
                artistName,
                debutDate
        );
    }
}