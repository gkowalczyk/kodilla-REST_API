package com.crud.tasks;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;
    @Mock
    private CreatedTrelloCardDto createdTrelloCardDto;
    @Mock
    private TrelloCardDto trelloCardDto;
    @Mock
    private TrelloCard trelloCard;

    @Test
    void shouldFetchEmptyList() {

        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", true));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test1", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", true));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        System.out.println(trelloBoards.get(0).getName());
        when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mappedTrelloBoards);
        System.out.println(trelloBoards.get(0).getName());
        System.out.println(mappedTrelloBoards.get(0).getName());
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(List.of());//list.of zwracamy pustą listę
        System.out.println(anyList());
        System.out.println(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());
        System.out.println(mappedTrelloBoards.get(0).getLists());

        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        System.out.println(trelloBoardDtos.size());

        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);

    }
    @Test
    void shouldFetchTrelloBoards() {
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertThat(trelloListDto.getId()).isEqualTo("1");
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }
    @Test
    void shouldCreatedTrelloCardDto() {

        //Given
        TrelloBudgetDto trelloBudgetDto = new TrelloBudgetDto(2, new TrelloAttachmentsByTypeDto(new TrelloTrelloDto(2, 3)));
        CreatedTrelloCardDto createdTrelloCardDtoNew = new CreatedTrelloCardDto("1", "card", "example.com", trelloBudgetDto);
        //TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listID");
        //TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listID");

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(any())).thenReturn(trelloCardDto);
        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDtoNew);

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("example.com", createdTrelloCardDto.getShortUrl());
        assertEquals(3, createdTrelloCardDto.getTrelloBudgetDto().getAttachmentByTypeDto().getTrello().getCard());
    }
}


