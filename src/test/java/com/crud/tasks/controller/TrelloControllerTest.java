package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)//konfigurowanie infrastruktury MVC
class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;//klasa pozwalająca na wykonywanie żądań http

    @MockBean// jest to adnotacja bardzo podobna do adnotacji @Mock, którą znasz z biblioteki Mockito.
    // Jest jedna bardzo ważna różnica! Adnotacja @MockBean
    // udostępnia mocka obiektu, który staje się beanem dla kontekstu Springa.
    private TrelloFacade trelloFacade;

    @Test
    void shouldFetchEmptyTrelloBoards() throws Exception {
        //given
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of());
        //when and then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/boards") //wysyłanie żądań
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0))); // długość listy 0
    }

    @Test
    void shouldFetchTrelloBoard() throws Exception {
        List<TrelloListDto> trelloListDto = List.of(new TrelloListDto(("1"), "list", false));
        List<TrelloBoardDto> trelloBoardDto = List.of(new TrelloBoardDto("1", "test", trelloListDto));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardDto);
        mockMvc
                // Trello board fields
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")))
// Trello board fields
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].name", Matchers.is("list")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].closed", Matchers.is(false)));
    }

    @Test
    void shouldCreateTrelloCard() throws Exception {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name",
                "description", "pos", "listID");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("232", "test", "google.com", new TrelloBudgetDto());
        when(trelloFacade.createdTrelloCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        Gson gson = new Gson();//udostępnia metody konwersji JSON-a na obiekt
        // oraz obiektu na JSON-a.
        String jsonContent = gson.toJson(trelloCardDto);
        //When and Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/trello/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("232")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", Matchers.is("google.com")));
    }
}