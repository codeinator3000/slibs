package com.slibs.slibs.infrastructure.support;


import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.slibs.slibs.entities.Library;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApiClient {
    @Value("${api.url}")
    String url;
    @Value("${api.key}")
    String key;
    private final WebClient client;
    
    public List<LibraryDto> getLibsDto(String repoName, int page, int pageSize) {
        var urlReq = UriComponentsBuilder.fromUriString(url + "search")
            .queryParam("q", "")
            .queryParam("page", page)
            .queryParam("per_page", pageSize)
            .queryParam("platforms", repoName)
            .queryParam("api_key", key)
            .toUriString();
        
        return client
            .get()
            .uri(urlReq)
            .retrieve()
            .bodyToFlux(LibraryDto.class)
            .collectList()
            .block();
    }
}
