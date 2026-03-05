package com.example.mahjongappassignment.service;

import com.example.mahjongappassignment.domain.Hand;
import com.example.mahjongappassignment.domain.HandParser;
import com.example.mahjongappassignment.domain.TenpaiCalculator;
import com.example.mahjongappassignment.domain.Tile;
import com.example.mahjongappassignment.exception.InvalidHandException;
import com.example.mahjongappassignment.model.TenpaiResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiichiTenpaiService {
    private final HandParser handParser;
    private final TenpaiCalculator tenpaiCalculator;

    public RiichiTenpaiService() {
        this(new HandParser(), new TenpaiCalculator());
    }

    RiichiTenpaiService(HandParser handParser, TenpaiCalculator tenpaiCalculator) {
        this.handParser = handParser;
        this.tenpaiCalculator = tenpaiCalculator;
    }

    public TenpaiResult checkTenpai(String handInput) {
        Hand hand = handParser.parse(handInput);
        if (hand.totalTiles() != 13) {
            throw new InvalidHandException(
                    "Hand must contain exactly 13 tiles for tenpai check, but got " + hand.totalTiles() + "."
            );
        }

        List<String> waitingTiles = tenpaiCalculator.findWinningDraws(hand)
                .stream()
                .map(Tile::notation)
                .toList();

        return new TenpaiResult(handInput, !waitingTiles.isEmpty(), waitingTiles);
    }
}
