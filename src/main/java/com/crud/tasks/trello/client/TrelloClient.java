package com.crud.tasks.trello.client;


import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;

    public List<TrelloBoardDto> getTrelloBoards() {
        //TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
        // trelloApiEndpoint + "members/grzegorzkowalczyk25/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
        //  TrelloBoardDto[].class
        //        );
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/grzegorzkowalczyk25/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build()
                .encode()
                .toUri();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
       // return Collections.emptyList();

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }

        return new ArrayList<>();
    }

}