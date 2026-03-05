package com.example.mahjongappassignment.domain;

public record Tile(TileType type) {
    public static Tile of(TileType type) {
        return new Tile(type);
    }

    public static Tile ofIndex(int index) {
        return new Tile(TileType.fromIndex(index));
    }

    public int index() {
        return type.index();
    }

    public String notation() {
        return type.notation();
    }
}
