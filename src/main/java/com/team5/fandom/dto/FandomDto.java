package com.team5.fandom.dto;

import com.team5.fandom.entity.Artist;
import com.team5.fandom.entity.Fandom;
import com.team5.fandom.entity.value.Level;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class FandomDto {
    private Integer fandomId;
    private String fandomName;
    private Level fandomLevel;
    private Integer fandomExp;
    private ArtistDto artistDto;

    // Fandom entity -> FandomDto 변환 메서드
    public static FandomDto toFandomDto(Fandom fandom) {
        return new FandomDto(
                fandom.getFandomId(),
                fandom.getFandomName(),
                fandom.getFandomLevel(),
                fandom.getFandomExp(),
                ArtistDto.toArtistDto(fandom.getArtist())
        );
    }

    // FandomDto -> Fandom entity 변환 메서드
    private Fandom toEntity(Artist artist) {
        return Fandom.of(

                fandomName,
                fandomLevel,
                fandomExp,
                artist
        );
    }
}