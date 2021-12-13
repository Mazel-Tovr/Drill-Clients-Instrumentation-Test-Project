package com.epam.dto;

public record SimpleDTO(int x, int y) {

    public int sum() {
        return x + y;
    }
}
