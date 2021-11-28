package com.example.tadoblog.service;

import com.example.tadoblog.data.Card;
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

    public void saveCard(Card card){
        jpaCardRepository.save(card);
    }
    public Page<Card> getCard(Pageable pageable){
        return jpaCardRepository.findAll(pageable);
    }
    public Card getCard(UUID id) {
        return jpaCardRepository.findById(id).orElseThrow(()-> new CardNotExistExeption(id));
    }
    public void updateCard(Card card){
        jpaCardRepository.save(card);
    }
    public void deleteCard(UUID id){
        jpaCardRepository.deleteById(id);
    }
    public List<Card> getAllCardsByTitle (String title){
        return jpaCardRepository.findCardDataByCardTitle(title);
    }
    public Card getOneCardData(UUID id){
        return jpaCardRepository.findCardDataById(id);
    }
}
