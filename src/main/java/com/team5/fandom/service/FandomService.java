package com.team5.fandom.service;

import com.team5.fandom.dto.FandomDto;
import com.team5.fandom.entity.Fandom;
import com.team5.fandom.repository.FandomRepository;
import com.team5.fandom.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FandomService {
    private final FandomRepository fandomRepository;
    private final PostRepository postRepository;

    public List<FandomDto> getFandoms() {
//		System.out.println(fandomRepository.findAllByOrderByFandomExpAtASC());
        return fandomRepository.findAllByOrderByFandomExpDesc().stream().map(FandomDto::toFandomDto).toList();
    }

    public List<FandomDto> getAllFandoms() {
        return fandomRepository.findAll().stream()
                .map(FandomDto::toFandomDto)
                .collect(Collectors.toList());
    }


    public FandomDto getByFandomName(String fandomName) {
        Fandom fandom = fandomRepository.findByFandomName(fandomName);
        if (fandom == null) {
            throw new IllegalArgumentException("Fandom name " + fandomName + " not found");
        }
        return FandomDto.toFandomDto(fandom);
    }

}
