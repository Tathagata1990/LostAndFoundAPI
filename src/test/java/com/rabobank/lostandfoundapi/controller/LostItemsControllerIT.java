package com.rabobank.lostandfoundapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LostItemsControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private final String LOCALHOST_URL = "http://localhost:";

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
    }

    @Test
    void givenValidDocument_whenUploadDocument_thenSuccess() {
        ClassPathResource classPathResource = new ClassPathResource("lost_items.pdf");

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", classPathResource);

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = testRestTemplate.postForEntity(LOCALHOST_URL + port + "/api/lost-items/upload", entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void givenItemsExist_whenGetLostItems_thenSuccess() {
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = testRestTemplate.getForEntity(LOCALHOST_URL + port + "/api/lost-items", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
