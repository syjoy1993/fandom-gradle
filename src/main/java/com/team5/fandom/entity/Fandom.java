package com.team5.fandom.entity;

import com.team5.fandom.entity.value.Level;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Fandom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fandomId;

    @Column(name = "fandom_name", nullable = false, length = 255)
    private String fandomName;

    @Enumerated(EnumType.STRING)
    @Column(name = "fandom_level")
    private Level fandomLevel;

    @Column(name = "fandom_exp", nullable = false)
    private Integer fandomExp;

    @ManyToOne
    @JoinColumn(name = "artist_id", unique = true)
    private Artist artist;


    private Fandom(String fandomName, Level fandomLevel, Integer fandomExp, Artist artist) {
        this.fandomName = fandomName;
        this.fandomLevel = fandomLevel;
        this.fandomExp = fandomExp;
        this.artist = artist;
    }



    //No Id
    @Builder
    public static Fandom of(String fandomName, Level fandomLevel, Integer fandomExp, Artist artist) {
        return new Fandom(fandomName, fandomLevel, fandomExp, artist);
    }


    
    
}
