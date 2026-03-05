package com.example.mahjongappassignment.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TenpaiCalculator {
    private static final Set<TileType> THIRTEEN_ORPHANS = EnumSet.of(
            TileType.M1, TileType.M9,
            TileType.P1, TileType.P9,
            TileType.S1, TileType.S9,
            TileType.NORTH, TileType.EAST, TileType.WEST, TileType.SOUTH,
            TileType.WHITE, TileType.RED, TileType.GREEN
    );

    public List<Tile> findWinningDraws(Hand hand) {
        List<Tile> waits = new ArrayList<>();
        int[] baseCounts = hand.copyCounts();
        for (TileType type : TileType.values()) {
            int i = type.index();
            if (baseCounts[i] >= 4) {
                continue;
            }
            int[] candidate = baseCounts.clone();
            candidate[i]++;
            if (isWinningHand(candidate)) {
                waits.add(Tile.of(type));
            }
        }
        return waits;
    }

    private boolean isWinningHand(int[] counts) {
        return isStandardWinningHand(counts) || isSevenPairs(counts) || isThirteenOrphans(counts);
    }

    private boolean isSevenPairs(int[] counts) {
        int pairs = 0;
        for (int c : counts) {
            if (c == 0) {
                continue;
            }
            if (c == 2) {
                pairs++;
            } else {
                return false;
            }
        }
        return pairs == 7;
    }

    private boolean isThirteenOrphans(int[] counts) {
        boolean hasPair = false;
        for (TileType type : TileType.values()) {
            if (!THIRTEEN_ORPHANS.contains(type) && counts[type.index()] > 0) {
                return false;
            }
        }
        for (TileType orphan : THIRTEEN_ORPHANS) {
            if (counts[orphan.index()] == 0) {
                return false;
            }
            if (counts[orphan.index()] >= 2) {
                hasPair = true;
            }
        }
        return hasPair;
    }

    private boolean isStandardWinningHand(int[] counts) {
        if (totalTiles(counts) != 14) {
            return false;
        }
        for (int i = 0; i < TileType.COUNT; i++) {
            if (counts[i] < 2) {
                continue;
            }
            counts[i] -= 2;
            if (canFormAllMelds(counts, new HashMap<>())) {
                counts[i] += 2;
                return true;
            }
            counts[i] += 2;
        }
        return false;
    }

    private boolean canFormAllMelds(int[] counts, Map<String, Boolean> memo) {
        String key = Arrays.toString(counts);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int first = -1;
        for (int i = 0; i < TileType.COUNT; i++) {
            if (counts[i] > 0) {
                first = i;
                break;
            }
        }

        if (first == -1) {
            memo.put(key, true);
            return true;
        }

        if (counts[first] >= 3) {
            counts[first] -= 3;
            if (canFormAllMelds(counts, memo)) {
                counts[first] += 3;
                memo.put(key, true);
                return true;
            }
            counts[first] += 3;
        }

        if (first < 27 && first % 9 <= 6 && counts[first + 1] > 0 && counts[first + 2] > 0) {
            counts[first]--;
            counts[first + 1]--;
            counts[first + 2]--;
            if (canFormAllMelds(counts, memo)) {
                counts[first]++;
                counts[first + 1]++;
                counts[first + 2]++;
                memo.put(key, true);
                return true;
            }
            counts[first]++;
            counts[first + 1]++;
            counts[first + 2]++;
        }

        memo.put(key, false);
        return false;
    }

    private int totalTiles(int[] counts) {
        int total = 0;
        for (int count : counts) {
            total += count;
        }
        return total;
    }
}
