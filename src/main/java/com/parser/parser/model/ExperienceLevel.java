package com.parser.parser.model;

import java.util.Arrays;
import java.util.Optional;

public enum ExperienceLevel {
    ANY(""),
    LESS_THAN_ONE_YEAR("0-1"),
    ONE_TO_THREE_YEARS("1-3"),
    THREE_TO_FIVE_YEARS("3-5"),
    MORE_THAN_FIVE("5plus");

    private final String experience;

    ExperienceLevel(String experience) {
        this.experience = experience;
    }

    public static Optional<ExperienceLevel> getByExperience(String experience) {
        return Arrays.stream(values())
                .filter(level -> level.experience.equals(experience))
                .findFirst();
    }
}
