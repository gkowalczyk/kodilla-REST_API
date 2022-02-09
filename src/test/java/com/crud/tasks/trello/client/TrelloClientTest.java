package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloBudgetDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloconfig;

    //@Mock
    //private CreatedTrelloCard createdTrelloCard;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException { //pobieranie listy trello
        when(trelloconfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloconfig.getTrelloAppKey()).thenReturn("test");
        when(trelloconfig.getTrelloToken()).thenReturn("test");
        when(trelloconfig.getTrelloUsername()).thenReturn("test");

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());
        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When

        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("Kodilla", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());

    }
    @Test
        public void shouldCreateCard() throws URISyntaxException {
        //Given
        when(trelloconfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloconfig.getTrelloAppKey()).thenReturn("test");
        when(trelloconfig.getTrelloToken()).thenReturn("test");
        // when(trelloconfig.getTrelloUsername()).thenReturn("test");
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "test task",
                "http://test.com",
                new TrelloBudgetDto()
        );
        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        //The
        assertEquals("1", newCard.getId());
        assertEquals("test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }
        @Test
        public void shouldReturnEmptyList() throws URISyntaxException {
            when(trelloconfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
            when(trelloconfig.getTrelloAppKey()).thenReturn("test");
            when(trelloconfig.getTrelloToken()).thenReturn("test");
            when(trelloconfig.getTrelloUsername()).thenReturn("test");
            TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
            trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());
            URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
            when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
            List<TrelloBoardDto> empty = trelloClient.getTrelloBoards();
            assertEquals(0,empty.size());
            System.out.println(empty);
        }
}