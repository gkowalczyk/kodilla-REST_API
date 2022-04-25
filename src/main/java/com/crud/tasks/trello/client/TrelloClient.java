package com.crud.tasks.trello.client;


import ch.qos.logback.classic.Logger;
import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;

@Component//
@RequiredArgsConstructor
@EqualsAndHashCode

public class TrelloClient { //

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TrelloClient.class);

    private final RestTemplate restTemplate;
    private final TrelloConfig trelloconfig;


    public List<TrelloBoardDto> getTrelloBoards() {


        //TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
        // trelloApiEndpoint + "members/grzegorzkowalczyk25/boards" + "?key=" + trelloAppKey + "&token=" + trelloToken,
        //  TrelloBoardDto[].class
        //        );
        URI url = UriComponentsBuilder.
                fromHttpUrl(trelloconfig.getTrelloApiEndpoint() + "/members/" + trelloconfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloconfig.getTrelloAppKey())
                .queryParam("token", trelloconfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()//  enkodowanie
                .toUri();

        // try{
        //   TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        //return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        // } catch (RestClientException e){
        //  LOGGER.error(e.getMessage(), e);
        //return new ArrayList<>();


        //Jako kolejny argument podajemy klasę, której obiekt chcemy utworzyć na podstawie odpowiedzi z serwera – można to określić mianem mapowania odpowiedzi z serwera na obiekt Javowy
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class); //
           // return Collections.emptyList();

            //if (boardsResponse != null) {
            //    return Arrays.asList(boardsResponse);


        // return new ArrayList<>();
        return ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
                //.stream()
               // .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
               // .filter(p -> p.getName().contains("Kodilla"))
               // .collect(Collectors.toList());
    } catch(
    RestClientException e)

    {
        LOGGER.error(e.getMessage(), e);
        return Collections.emptyList();
    }

}
    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloconfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloconfig.getTrelloAppKey())
                .queryParam("token", trelloconfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();
        return restTemplate.postForObject(url,null, CreatedTrelloCardDto.class);
    }

}