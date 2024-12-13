package com.team5.fandom.entity.value;

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
}