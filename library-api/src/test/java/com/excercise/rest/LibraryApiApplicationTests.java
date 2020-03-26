package com.excercise.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.excercise.rest.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LibraryApiApplicationTests {

    Logger log = LoggerFactory.getLogger(LibraryApiApplicationTests.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int serverPort;

    private String serverUrl;

    private ObjectMapper objectMapper;

    @Test
    void testAddBook() throws  Exception{
        final String expectedStatus="SUCCESS";
        final int expectedResponseCode=201;
        final String expectedReponse="Added Book01 successfully.";

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        final String requestBody =  objectMapper
                .writeValueAsString(
                        new Book(01, "Book01","Test_01","Friction"));
        final HttpEntity<String> request = new HttpEntity<String>(requestBody ,header);
        final ResponseEntity responseEntity = testRestTemplate.postForEntity(serverUrl, request, String.class);


        assertNotNull(responseEntity);

        final int responseStatusCode = responseEntity.getStatusCode().value();
        final String responseBody =  (String)responseEntity.getBody();
        assertEquals(expectedResponseCode,responseStatusCode);
        assertNotNull(responseBody);


    }

    @Test
    void testAuthorNameMissing() throws  Exception{


        final  int expectedStatusCode = 500;
        final  String expectedStatus = "ERROR";
        final  String expectedResponse = "author: null found, string expected";

        // set request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        final String requestBody =  objectMapper
                .writeValueAsString(
                        new Book());

        final HttpEntity<String> request = new HttpEntity<String>(requestBody ,requestHeaders);


        final ResponseEntity responseEntity = testRestTemplate.postForEntity(serverUrl, request, String.class);


        assertNotNull(responseEntity);

        // get response HTTP Code and payload
        final int responseStatusCode = responseEntity.getStatusCode().value();
        final String responseBody =  (String)responseEntity.getBody();


        assertEquals(expectedStatusCode,responseStatusCode);
        assertNotNull(responseBody);


    }
    @BeforeEach
    void init(){

        serverUrl = "http://localhost:" + serverPort + "/library-api/addBook";
        objectMapper = new ObjectMapper();
    }

}
