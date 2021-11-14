package com.example.tadoblog.service;

import com.example.tadoblog.data.CardData;
import com.example.tadoblog.exeption.CardNotExistExeption;
import com.example.tadoblog.repository.JPACardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CardService {
    private final JPACardRepository jpaCardRepository;

    public CardService(JPACardRepository jpaCardRepository) {
        this.jpaCardRepository = jpaCardRepository;
    }

    public void saveCard(CardData cardData){
        jpaCardRepository.save(cardData);
    }
    public Page<CardData> getCard(Pageable pageable){
        return jpaCardRepository.findAll(pageable);
    }
    public CardData getCard(UUID id) {
        return jpaCardRepository.findById(id).orElseThrow(()-> new CardNotExistExeption(id));
    }
    public void updateCard(CardData cardData){
        jpaCardRepository.save(cardData);
    }
    public void deleteCard(UUID id){
        jpaCardRepository.deleteById(id);
    }
    public List<CardData> getAllCardsByTitle (String title){
        return jpaCardRepository.findCardDataByCardTitle(title);
    }
}
