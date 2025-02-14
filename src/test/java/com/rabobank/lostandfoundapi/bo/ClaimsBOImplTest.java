package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.service.ClaimsService;
import com.rabobank.lostandfoundapi.service.LostItemsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClaimsBOImplTest {

    @Mock
    private ClaimsService claimsService;

    @Mock
    private LostItemsService lostItemsService;

    @InjectMocks
    private ClaimsBO claimsBO;

    @Test
    void givenItemsExist_whenClaimLostItem_thenSuccess() {
        when(lostItemsService.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(generateLostItem());


    }

    private LostItem generateLostItem() {
        return LostItem.builder()
                .itemName("Laptop")
                .place("Airport")
                .quantity(1)
                .build();
    }
}
