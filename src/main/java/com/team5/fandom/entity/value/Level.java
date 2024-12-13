package com.team5.fandom.entity.value;

public enum Level {
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    DIAMOND("Diamond");

    private final String levelName;

    Level(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }
}