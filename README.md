# Riichi Mahjong Tenpai Checker (Backend)

This project is a Spring Boot backend that checks whether a **13-tile Riichi Mahjong hand** is in **tenpai** (one tile away from a winning hand).

It returns:
- `tenpai: true/false`
- `waitingTiles`: list of tiles that complete the hand

## What It Supports

- Standard winning shape: 4 melds + 1 pair
- Seven pairs (`chiitoitsu`)
- Thirteen orphans (`kokushi musou`)
- Validates:
  - input format
  - tile count (must be exactly 13 for tenpai check)
  - no tile appears more than 4 times

## Tile Input Format

### Suited tiles
- `m` = manzu
- `p` = pinzu
- `s` = souzu

Examples:
- `123m` = 1m 2m 3m
- `1145p` = 1p 1p 4p 5p

### Honor tiles (custom format)
- Winds: `n e w s` (north east west south)
- Dragons: `wh r g` (white red green)

Examples:
- `n e s wh r`
- compact form without spaces is also allowed, e.g. `newswhrg`

### Alternative honor input
- `z` format is also supported (`1-7z`) for honors.

## API

### Endpoint
- `POST /api/mahjong/tenpai`

### Request JSON
```json
{
  "hand": "123456789m 1145p"
}
```

### Response JSON
```json
{
  "hand": "123456789m 1145p",
  "tenpai": true,
  "waitingTiles": ["3p", "6p"]
}
```

## Run the App

From project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Server starts on `http://localhost:8080`.

## Fast Manual Testing (PowerShell Helper)

After the app is running, define a helper once in your terminal:

```powershell
function tenpai($hand) {
  $body = @{ hand = $hand } | ConvertTo-Json
  Invoke-RestMethod -Method Post `
    -Uri "http://localhost:8080/api/mahjong/tenpai" `
    -ContentType "application/json" `
    -Body $body
}
```

Then test quickly:

```powershell
tenpai "123456789m 1145p"
tenpai "19m19p19snewswhrg"
tenpai "123m456m789mwhwhwhg"
```

## Run Tests

```powershell
.\mvnw.cmd test
```

## Quick API Test (PowerShell)

```powershell
$body = @{ hand = "123456789m 1145p" } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/mahjong/tenpai" -ContentType "application/json" -Body $body
```

Another example using honors:

```powershell
$body = @{ hand = "123m456m789mwhwhwhg" } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/mahjong/tenpai" -ContentType "application/json" -Body $body
```

## Notes

- Input may include spaces; they are ignored.
- This checker focuses on tenpai/winning-structure validation, not full scoring/yaku calculation.
