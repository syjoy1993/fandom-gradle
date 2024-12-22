package com.team5.fandom.entity.value;

import java.util.Arrays;

public enum Tag {
    NOVEL("Novel"),
    CHAT("Chat"),
    NEWS("News");

    private final String hashTag;

    Tag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getHashTag() {
        return hashTag;
    }

    public static Tag getInstance(String tagType) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getHashTag().equals(tagType))
                .findFirst()
                .orElseThrow();
    }
}