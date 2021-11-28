package com.example.tadoblog.repository;

import com.example.tadoblog.data.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JPACardRepository extends JpaRepository<Card, UUID> {
    Card findCardDataById(UUID id);
}
