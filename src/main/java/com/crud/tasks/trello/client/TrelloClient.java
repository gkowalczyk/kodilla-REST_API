package com.crud.tasks.trello.client;


import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;
    private TrelloBoardDto trelloBoardDto;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUsername;

    public List<TrelloBoardDto> getTrelloBoards() {
        //TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
        // trelloApiEndpoint + "members/grzegorzkowalczyk25/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
        //  TrelloBoardDto[].class
        //        );
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername+ "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
               .build()
                .encode()
                .toUri();

        //Jako kolejny argument podajemy klasę, której obiekt chcemy utworzyć na podstawie odpowiedzi z serwera – można to określić mianem mapowania odpowiedzi z serwera na obiekt Javowy
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class); //
        // return Collections.emptyList();

        //if (boardsResponse != null) {
        //    return Arrays.asList(boardsResponse);
        // }

        // return new ArrayList<>();
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)

              .orElse(Collections.emptyList());
    }

}