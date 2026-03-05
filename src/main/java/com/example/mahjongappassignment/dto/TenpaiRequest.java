package com.example.mahjongappassignment.dto;

import jakarta.validation.constraints.NotBlank;

public record TenpaiRequest(@NotBlank String hand) {
}
