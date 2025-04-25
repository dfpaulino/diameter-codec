package org.example.pcc.client;

import org.example.pcc.contexts.SessionInitContext;
import org.example.pcc.contexts.SessionUpdateContext;
import org.example.pcc.dto.SessionPolicy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PccRestClient {

    private final WebClient webClient;

    public PccRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO define requestBody Gx -> SessionPolicyContext
    public Mono<SessionPolicy> createSession(@RequestBody SessionInitContext context) {
        return webClient.post()
                .uri("/npcf-smpolicycontrol/v1/sm-policies")
                .bodyValue(context)
                .retrieve()
                .bodyToMono(SessionPolicy.class);

    }

    // TODO define requestBody Gx -> SessionPolicyContext
    public Mono<SessionPolicy> updateSession(@PathVariable String smPolicyId, @RequestBody SessionUpdateContext context) {
        return webClient.post()
                .uri("/npcf-smpolicycontrol/v1/sm-policies/{smPolicyId}/update",smPolicyId)
                .bodyValue(context)
                .retrieve()
                .bodyToMono(SessionPolicy.class);

    }

    // TODO define requestBody Gx -> SessionPolicyContext
    public Mono<Void> deleteSession(@PathVariable String smPolicyId, @RequestBody SessionUpdateContext context) {
        return webClient.post()
                .uri("/npcf-smpolicycontrol/v1/sm-policies/{smPolicyId}/delete",smPolicyId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);

    }


}
