package org.example.pcc.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.example.pcc.dto.PccRule;
import org.example.pcc.dto.SessionPolicy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

class PccRestClientTest {
    private MockWebServer mockBackEnd;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws IOException {
        Logger.getLogger(MockWebServer.class.getName()).setLevel(Level.FINE);
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @Test
    public void createSession() throws JsonProcessingException {
        int port = mockBackEnd.getPort();
        SessionPolicy sessionPolicy = generateSessionPolicyInit();
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(sessionPolicy)));


    }


    private SessionPolicy generateSessionPolicyInit() {
        SessionPolicy sessionPolicy = new SessionPolicy();
        PccRule pccRule = PccRule.builder()
                .name("pccRule1")
                .precedence(1)
                .flowInfos( Arrays.asList(flowUp,flowDown))
                .refQosData(Arrays.asList("qos1"))
                .build();
        sessionPolicy.setSessionRules();
        sessionPolicy.setPccRules();
        sessionPolicy.setOnline(true);
        return sessionPolicy;
    }
}