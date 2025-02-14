package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.Claim;
import com.rabobank.lostandfoundapi.repository.ClaimsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClaimsServiceImplTest {

    @Mock
    private ClaimsRepository claimsRepository;

    @InjectMocks
    private ClaimsServiceImpl claimsService;

    @Test
    void givenClaimsExist_whenGetClaims_thenReturnClaims() {
        when(claimsRepository.findAll()).thenReturn(List.of(generateClaim()));

        List<Claim> claims = claimsService.getClaimedItems();

        assertEquals(1, claims.size());
    }

    private Claim generateClaim() {
        return Claim.builder()
                .lostItemName("Laptop")
                .userId(1001L)
                .userName("User")
                .lostItemQuantity(2)
                .build();
    }
}
