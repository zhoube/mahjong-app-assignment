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

### Honor tiles (`z` format)
Use numbers under `z`:
- `1z` = East
- `2z` = South
- `3z` = West
- `4z` = North
- `5z` = White dragon
- `6z` = Green dragon
- `7z` = Red dragon

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

## Run Tests

```powershell
.\mvnw.cmd test
```

## API Testing (Postman)

Use:
- Method: `POST`
- URL: `http://localhost:8080/api/mahjong/tenpai`
- Header: `Content-Type: application/json`
- Body (raw JSON):

```json
{
  "hand": "123456789m 1145p"
}
```

More sample hands:
- `19m19p19s1234567z`
- `123m456m789m5556z`

## Notes

- Input may include spaces; they are ignored.
- This checker focuses on tenpai/winning-structure validation, not full scoring/yaku calculation.
