package com.team5.fandom.entity.value;

public enum Tag {
    NOVEL("NOVEL"),
    CHAT("CHAT"),
    NEWS("NEWS");

    private final String hashTag;

    Tag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getHashTag() {
        return hashTag;
    }
}