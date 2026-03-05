package com.example.mahjongappassignment.domain;

public enum TileType {
    M1(0, "1m"),
    M2(1, "2m"),
    M3(2, "3m"),
    M4(3, "4m"),
    M5(4, "5m"),
    M6(5, "6m"),
    M7(6, "7m"),
    M8(7, "8m"),
    M9(8, "9m"),
    P1(9, "1p"),
    P2(10, "2p"),
    P3(11, "3p"),
    P4(12, "4p"),
    P5(13, "5p"),
    P6(14, "6p"),
    P7(15, "7p"),
    P8(16, "8p"),
    P9(17, "9p"),
    S1(18, "1s"),
    S2(19, "2s"),
    S3(20, "3s"),
    S4(21, "4s"),
    S5(22, "5s"),
    S6(23, "6s"),
    S7(24, "7s"),
    S8(25, "8s"),
    S9(26, "9s"),
    NORTH(27, "n"),
    EAST(28, "e"),
    WEST(29, "w"),
    SOUTH(30, "s"),
    WHITE(31, "wh"),
    RED(32, "r"),
    GREEN(33, "g");

    public static final int COUNT = values().length;
    private static final TileType[] BY_INDEX = values();

    private final int index;
    private final String notation;

    TileType(int index, String notation) {
        this.index = index;
        this.notation = notation;
    }

    public int index() {
        return index;
    }

    public String notation() {
        return notation;
    }

    public static TileType fromIndex(int index) {
        if (index < 0 || index >= COUNT) {
            throw new IllegalArgumentException("Tile index must be between 0 and 33.");
        }
        return BY_INDEX[index];
    }
}
