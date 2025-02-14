package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.exception.ItemNotFoundException;
import com.rabobank.lostandfoundapi.repository.LostItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LostItemsServiceImplTest {

    @Mock
    private LostItemsRepository lostItemsRepository;

    @InjectMocks
    private LostItemsServiceImpl lostItemsService;

    @Test
    void givenItemsExist_whenGetLostItems_thenReturnLostItems() {
        when(lostItemsRepository.findAll()).thenReturn(List.of(generateLostItem()));

        List<LostItem> lostItems = lostItemsService.getLostItems();

        assertEquals(1, lostItems.size());
    }

    @Test
    void givenItemsDoNotExist_whenGetLostItems_thenReturnEmptyList() {
        when(lostItemsRepository.findAll()).thenReturn(Collections.emptyList());

        List<LostItem> lostItems = lostItemsService.getLostItems();

        assertEquals(0, lostItems.size());
    }

    @Test
    void givenItemsExist_whenFindLostItemByItemNameAndPlace_thenReturnLostItem() {
        when(lostItemsRepository.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(Optional.of(generateLostItem()));

        LostItem lostItem = lostItemsService.findLostItemByItemNameAndPlace("Laptop", "Airport");

        assertEquals("Laptop", lostItem.getItemName());
        assertEquals("Airport", lostItem.getPlace());
    }

    @Test
    void givenItemsDoNotExist_whenFindLostItemByItemNameAndPlace_thenThrowItemNotFoundException() {
        when(lostItemsRepository.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(Optional.empty());

        ItemNotFoundException e = assertThrows(ItemNotFoundException.class,
                () -> lostItemsService.findLostItemByItemNameAndPlace("Laptop", "Airport"));

        assertEquals("Lost item not found", e.getMessage());
    }

    private LostItem generateLostItem() {
        return LostItem.builder()
                .itemName("Laptop")
                .place("Airport")
                .quantity(1)
                .build();
    }
}
