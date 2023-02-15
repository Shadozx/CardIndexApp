package com.shadoww.CardIndexApp.repositories;

import com.shadoww.CardIndexApp.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Card, Integer> {
}
