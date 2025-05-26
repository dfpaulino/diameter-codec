package org.example.pcc.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import okhttp3.mockwebserver.RecordedRequest;
import org.example.pcc.contexts.SessionInitContext;
import org.example.pcc.dto.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PccRestClientTest {
    private MockWebServer mockBackEnd;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws IOException {
        Logger.getLogger(MockWebServer.class.getName()).setLevel(Level.ALL);
        mockBackEnd = new MockWebServer();

        mockBackEnd.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @Test
    public void createSession() throws JsonProcessingException, InterruptedException {
        int port = mockBackEnd.getPort();
        SessionPolicy sessionPolicy = generateSessionPolicyInit();
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200).addHeader("Content-Type","application/json")
                .setBody(objectMapper.writeValueAsString(sessionPolicy)));
        String baseUrl = "http://localhost:%s".formatted(port);
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        PccRestClient pccRestClient = new PccRestClient(webClient);
        SessionInitContext sessionInitContext = SessionInitContext.builder()
                .dnn("meo.pt")
                .pduSessionId(123456789L)
                .gpsi("967178860")
                .supi("1111111111111")
                .ipv4Address("10.230.133.23")
                .ratType(4)
                .pei("112-233-5565-00")
                .build();
        Mono<SessionPolicy> sessionPolicyMono = pccRestClient.createSession(sessionInitContext);

        StepVerifier.create(sessionPolicyMono)
                //check that pccRUles not empty
                .expectNextMatches(x -> !x.getPccRules().isEmpty() && x.getPccRules().containsKey("pccRule1"))
                .expectComplete().verify();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest(1, TimeUnit.SECONDS);
        assertThat(recordedRequest.getMethod()).isEqualTo("POST");
        assertThat(recordedRequest.getPath()).isEqualTo("/npcf-smpolicycontrol/v1/sm-policies");




    }







    private SessionPolicy generateSessionPolicyInit() {
        SessionPolicy sessionPolicy = new SessionPolicy();

        SessionRule sessionRule = new SessionRule();
        ApnAggregatedBitRate ambr = new ApnAggregatedBitRate();
        ambr.setDownlink(1204);
        ambr.setUplink(256);
        sessionRule.setAmbr(ambr);
        AuthorizedDefaultQos authorizedDefaultQos = new AuthorizedDefaultQos();
        authorizedDefaultQos.set_5qi(9L);
        authorizedDefaultQos.setMaxbrUl(1024);
        authorizedDefaultQos.setMaxbrDl(1024);

        sessionRule.setAuthorizedDefaultQos(authorizedDefaultQos);


        FlowInformation flowInformationDown = new FlowInformation();
        flowInformationDown.setFlowDescription("permit in 17 from 172.17.241.255 17105 to 172.16.20.111/32");
        flowInformationDown.setFlowDirection("DOWN"); //downlink 1

        FlowInformation flowInformationUp = new FlowInformation();
        flowInformationUp.setFlowDescription("permit in 17 from 172.17.241.255 17105 to 172.16.20.111/32");
        flowInformationUp.setFlowDirection("UP"); //upLink 2


        PccRule pccRule = new PccRule();
        pccRule.setName("pccRule1");
        pccRule.setPrecedence(1);
        pccRule.setFlowInfos(Arrays.asList(flowInformationDown, flowInformationUp));
        pccRule.setRefQosData(Arrays.asList("qos1"));


        PccRule pccRule2 =new  PccRule();
        pccRule2.setName("pccRule2");
        pccRule2.setPrecedence(1);
        pccRule2.setFlowInfos(Arrays.asList(flowInformationDown, flowInformationUp));
        pccRule2.setRefQosData(Arrays.asList("qos2"));

        //session Qos
        sessionPolicy.setSessionRules(Map.of("sessionRule", sessionRule));
        // charging Rule install
        sessionPolicy.setPccRules(Map.of("pccRule1", pccRule, "pccRule2", pccRule2));
        sessionPolicy.setOnline(true);
        return sessionPolicy;
    }
}