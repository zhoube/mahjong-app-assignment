package com.example.mahjongappassignment.domain;

import com.example.mahjongappassignment.exception.InvalidHandException;

import java.util.Locale;

public class HandParser {
    public Hand parse(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidHandException("Hand cannot be empty.");
        }

        String normalized = input.replaceAll("\\s+", "").toLowerCase(Locale.ROOT);
        Hand hand = new Hand();

        int i = 0;
        while (i < normalized.length()) {
            char c = normalized.charAt(i);

            if (Character.isDigit(c)) {
                int start = i;
                while (i < normalized.length() && Character.isDigit(normalized.charAt(i))) {
                    i++;
                }
                if (i >= normalized.length()) {
                    throw new InvalidHandException("Digit group must be followed by suit (m/p/s/z).");
                }
                char suit = normalized.charAt(i);
                if (suit != 'm' && suit != 'p' && suit != 's' && suit != 'z') {
                    throw new InvalidHandException("Invalid suit '" + suit + "'. Use m/p/s/z.");
                }

                String digits = normalized.substring(start, i);
                for (char d : digits.toCharArray()) {
                    hand.addTile(Tile.of(toTileType(d, suit)));
                }
                i++;
                continue;
            }

            throw new InvalidHandException("Invalid token '" + c + "'.");
        }

        return hand;
    }

    private TileType toTileType(char digit, char suit) {
        if (digit < '1' || digit > '9') {
            throw new InvalidHandException("Digits must be from 1 to 9.");
        }

        int value = digit - '0';
        if (suit == 'z' && value > 7) {
            throw new InvalidHandException("Honor suit z only supports 1-7.");
        }

        int index = switch (suit) {
            case 'm' -> value - 1;
            case 'p' -> 9 + (value - 1);
            case 's' -> 18 + (value - 1);
            case 'z' -> 27 + (value - 1);
            default -> throw new InvalidHandException("Invalid suit.");
        };
        return TileType.fromIndex(index);
    }
}
