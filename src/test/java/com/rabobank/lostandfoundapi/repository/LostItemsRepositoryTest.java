package com.rabobank.lostandfoundapi.repository;

import com.rabobank.lostandfoundapi.entity.LostItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class LostItemsRepositoryTest {

    @Autowired
    private LostItemsRepository lostItemsRepository;

    @Test
    void givenItemExists_whenFindLostItemByItemNameAndPlace_thenReturnLostItems() {
        lostItemsRepository.save(generateLostItem());

        Optional<LostItem> lostItem = lostItemsRepository.findLostItemByItemNameAndPlace("Laptop", "Airport");

        assertTrue(lostItem.isPresent());
    }

    @Test
    void givenItemDoesNotExist_whenFindLostItemByItemNameAndPlace_thenReturnEmptyOptional() {
        Optional<LostItem> lostItem = lostItemsRepository.findLostItemByItemNameAndPlace("Laptop", "Airport");

        assertFalse(lostItem.isPresent());
    }

    @Test
    void givenItemDoesNotMatch_whenFindLostItemByItemNameAndPlace_thenReturnEmptyOptional() {
        lostItemsRepository.save(generateLostItem());

        Optional<LostItem> lostItem = lostItemsRepository.findLostItemByItemNameAndPlace("Laptop", "Railway station");

        assertFalse(lostItem.isPresent());
    }

    private LostItem generateLostItem() {
        return LostItem.builder()
                .itemName("Laptop")
                .place("Airport")
                .quantity(1)
                .build();
    }
}
