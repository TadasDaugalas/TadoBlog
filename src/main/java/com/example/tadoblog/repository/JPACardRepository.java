package com.example.tadoblog.repository;

import com.example.tadoblog.data.CardData;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface JPACardRepository extends JpaRepository<CardData, Id> {
    List<CardData> findCardDataByCardTitle(String title);
}
