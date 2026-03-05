package com.example.mahjongappassignment.domain;

import com.example.mahjongappassignment.exception.InvalidHandException;

public class Hand {
    private final int[] counts = new int[TileType.COUNT];

    public void addTile(Tile tile) {
        int index = tile.index();
        counts[index]++;
        if (counts[index] > 4) {
            throw new InvalidHandException("Tile " + tile.notation() + " appears more than 4 times.");
        }
    }

    public int totalTiles() {
        int total = 0;
        for (int count : counts) {
            total += count;
        }
        return total;
    }

    public int tileCount(int index) {
        return counts[index];
    }

    public int tileCount(TileType type) {
        return counts[type.index()];
    }

    public int[] copyCounts() {
        return counts.clone();
    }
}
