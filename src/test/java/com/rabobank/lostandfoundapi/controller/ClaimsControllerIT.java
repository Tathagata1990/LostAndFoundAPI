package com.rabobank.lostandfoundapi.controller;

import com.rabobank.lostandfoundapi.controller.request.ClaimDTO;
import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.entity.LostItem;
import com.rabobank.lostandfoundapi.repository.LostItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClaimsControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private LostItemsRepository lostItemsRepository;

    @LocalServerPort
    private int port;

    private final String LOCALHOST_URL = "http://localhost:";

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void givenValidClaimRequest_whenClaimLostItems_thenSuccess() {
        lostItemsRepository.save(generateLostItem("Keys", "Airport"));
        HttpEntity<ClaimRequest> entity = new HttpEntity<>(generateClaimRequest("Keys", "Airport"), headers);

        ResponseEntity<String> response = testRestTemplate.postForEntity(LOCALHOST_URL + port + "/api/claims/claim", entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void givenRequestedItemsDoNotExist_whenClaimLostItems_thenNotFound() {
        HttpEntity<ClaimRequest> entity = new HttpEntity<>(generateClaimRequest("Sunglasses", "Airport"), headers);

        ResponseEntity<String> response = testRestTemplate.postForEntity(LOCALHOST_URL + port + "/api/claims/claim", entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private LostItem generateLostItem(String itemName, String place) {
        return LostItem.builder()
                .itemName(itemName)
                .place(place)
                .quantity(2)
                .build();
    }

    private ClaimRequest generateClaimRequest(String itemName, String place) {
        ClaimDTO claimDTO = ClaimDTO.builder()
                .lostItemName(itemName)
                .lostItemPlace(place)
                .lostItemQuantity(1)
                .build();
        return ClaimRequest.builder()
                .claimItem(claimDTO)
                .userId(1001L)
                .build();
    }
}
