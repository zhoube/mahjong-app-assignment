package com.example.mahjongappassignment.controller;

import com.example.mahjongappassignment.dto.TenpaiRequest;
import com.example.mahjongappassignment.dto.TenpaiResponse;
import com.example.mahjongappassignment.model.TenpaiResult;
import com.example.mahjongappassignment.service.RiichiTenpaiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mahjong")
public class TenpaiController {
    private final RiichiTenpaiService riichiTenpaiService;

    public TenpaiController(RiichiTenpaiService riichiTenpaiService) {
        this.riichiTenpaiService = riichiTenpaiService;
    }

    @PostMapping("/tenpai")
    public TenpaiResponse checkTenpai(@Valid @RequestBody TenpaiRequest request) {
        TenpaiResult result = riichiTenpaiService.checkTenpai(request.hand());
        return new TenpaiResponse(result.hand(), result.tenpai(), result.waitingTiles());
    }
}
