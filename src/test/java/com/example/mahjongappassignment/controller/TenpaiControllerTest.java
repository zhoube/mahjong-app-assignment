package com.example.mahjongappassignment.controller;

import com.example.mahjongappassignment.dto.TenpaiRequest;
import com.example.mahjongappassignment.dto.TenpaiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TenpaiControllerTest {
    @Autowired
    private TenpaiController controller;

    @Test
    void shouldReturnTenpaiResponse() {
        TenpaiResponse response = controller.checkTenpai(new TenpaiRequest("123456789m 1145p"));

        assertTrue(response.tenpai());
        assertEquals(2, response.waitingTiles().size());
    }

    @Test
    void shouldThrowForInvalidInput() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> controller.checkTenpai(new TenpaiRequest("123m")));
        assertTrue(ex.getMessage().contains("exactly 13"));
    }
}
