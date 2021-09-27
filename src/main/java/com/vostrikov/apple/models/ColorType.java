package com.vostrikov.apple.models;

public enum ColorType {
    GREEN ("Green"),
    RED ("Red"),
    YELLOW ("Yellow");

    private final String name;

    ColorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
