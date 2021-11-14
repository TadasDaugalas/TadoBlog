package com.example.tadoblog.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
public class CardNotExistExeption extends RuntimeException {
    private final Id cardId;
}
