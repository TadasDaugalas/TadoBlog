package com.example.tadoblog.repository;

import com.example.tadoblog.data.CardData;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import java.util.UUID;

public interface JPACardRepository extends JpaRepository<CardData, UUID> {
    List<CardData> findCardDataByCardTitle(String title);
    CardData findCardDataById(UUID id);
}
