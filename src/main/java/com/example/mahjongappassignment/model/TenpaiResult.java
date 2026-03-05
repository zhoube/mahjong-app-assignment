package com.example.mahjongappassignment.model;

import java.util.List;

public record TenpaiResult(
        String hand,
        boolean tenpai,
        List<String> waitingTiles
) {
}
