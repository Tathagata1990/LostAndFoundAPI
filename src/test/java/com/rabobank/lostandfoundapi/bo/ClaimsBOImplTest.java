package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.request.ClaimDTO;
import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.entity.Claim;
import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.service.ClaimsService;
import com.rabobank.lostandfoundapi.service.LostItemsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClaimsBOImplTest {

    @Mock
    private ClaimsService claimsService;

    @Mock
    private LostItemsService lostItemsService;

    @InjectMocks
    private ClaimsBOImpl claimsBO;

    @Test
    void givenItemsExist_whenClaimLostItem_thenSuccess() {
        LostItem lostItem = generateLostItem(2);
        when(lostItemsService.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(lostItem);
        doNothing().when(claimsService).claimItem(any(Claim.class));

        claimsBO.claimLostItem(generateClaimRequest(1));

        verify(lostItemsService, times(1)).findLostItemByItemNameAndPlace(anyString(), anyString());
        verify(lostItemsService, times(1)).saveLostItem(lostItem);
        verify(claimsService, times(1)).claimItem(any(Claim.class));
    }

    @Test
    void givenRequestedNumberOfItemsMoreThanTheExistingItems_whenClaimLostItem_thenThrowIllegalArgumentException() {
        LostItem lostItem = generateLostItem(1);
        when(lostItemsService.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(lostItem);

        assertThrows(IllegalArgumentException.class, () -> claimsBO.claimLostItem(generateClaimRequest(2)));

        verify(lostItemsService, times(1)).findLostItemByItemNameAndPlace(anyString(), anyString());
        verify(lostItemsService, never()).saveLostItem(any());
        verify(claimsService, never()).claimItem(any());
    }

    @Test
    void givenRequestedNumberOfItemsSameAsTheExistingItems_whenClaimLostItem_shouldDeleteLostItem() {
        LostItem lostItem = generateLostItem(1);
        when(lostItemsService.findLostItemByItemNameAndPlace(anyString(), anyString())).thenReturn(lostItem);
        doNothing().when(claimsService).claimItem(any(Claim.class));

        claimsBO.claimLostItem(generateClaimRequest(1));

        verify(lostItemsService, times(1)).findLostItemByItemNameAndPlace(anyString(), anyString());
        verify(lostItemsService, times(1)).deleteLostItem(lostItem);
        verify(claimsService, times(1)).claimItem(any(Claim.class));
    }

    private LostItem generateLostItem(Integer quantity) {
        return LostItem.builder()
                .itemName("Laptop")
                .place("Airport")
                .quantity(quantity)
                .build();
    }

    private ClaimRequest generateClaimRequest(Integer quantity) {
        ClaimDTO claimDTO = ClaimDTO.builder()
                .lostItemName("Laptop")
                .lostItemPlace("Airport")
                .lostItemQuantity(quantity)
                .build();
        return ClaimRequest.builder()
                .claimItem(claimDTO)
                .userId(1001L)
                .build();
    }
}
