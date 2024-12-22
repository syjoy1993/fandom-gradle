package com.team5.fandom.entity.value;

import java.util.Arrays;

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

    public String getLevel() {
        return levelName;
    }

    public static Level getInstance(String levelGrade) {
        return Arrays.stream(Level.values())
                .filter(level -> level.getLevel().equals(levelGrade))
                .findFirst()
                .orElseThrow();
    }
}