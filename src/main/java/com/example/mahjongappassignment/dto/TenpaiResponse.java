package com.example.mahjongappassignment.dto;

import java.util.List;

public record TenpaiResponse(
        String hand,
        boolean tenpai,
        List<String> waitingTiles
) {
}
