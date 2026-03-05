package com.example.mahjongappassignment.service;

import com.example.mahjongappassignment.exception.InvalidHandException;
import com.example.mahjongappassignment.model.TenpaiResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiichiTenpaiServiceTest {
    private final RiichiTenpaiService service = new RiichiTenpaiService();

    @Test
    void shouldDetectTenpaiForProvidedExample() {
        TenpaiResult response = service.checkTenpai("123456789m 1145p");

        assertTrue(response.tenpai());
        assertEquals(2, response.waitingTiles().size());
        assertTrue(response.waitingTiles().contains("3p"));
        assertTrue(response.waitingTiles().contains("6p"));
    }

    @Test
    void shouldDetectNotTenpai() {
        TenpaiResult response = service.checkTenpai("123456789m147p9s");

        assertFalse(response.tenpai());
        assertTrue(response.waitingTiles().isEmpty());
    }

    @Test
    void shouldSupportSevenPairsTenpai() {
        TenpaiResult response = service.checkTenpai("112233m445566p7s");

        assertTrue(response.tenpai());
        assertEquals(1, response.waitingTiles().size());
        assertEquals("7s", response.waitingTiles().get(0));
    }

    @Test
    void shouldSupportThirteenOrphansThirteenSidedWait() {
        TenpaiResult response = service.checkTenpai("19m19p19s1234567z");

        assertTrue(response.tenpai());
        assertEquals(13, response.waitingTiles().size());
    }

    @Test
    void shouldSupportHonorZNotation() {
        TenpaiResult response = service.checkTenpai("123m456m789m5556z");

        assertTrue(response.tenpai());
        assertEquals(1, response.waitingTiles().size());
        assertTrue(response.waitingTiles().contains("6z"));
    }

    @Test
    void shouldRejectWrongTileCount() {
        InvalidHandException ex = assertThrows(
                InvalidHandException.class,
                () -> service.checkTenpai("123m")
        );
        assertTrue(ex.getMessage().contains("exactly 13"));
    }
}
