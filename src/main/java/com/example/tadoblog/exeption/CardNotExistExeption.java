package com.example.tadoblog.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CardNotExistExeption extends RuntimeException {
    private final UUID cardId;
}
