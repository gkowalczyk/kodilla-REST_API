package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrelloFacade {

    private final TrelloValidator trelloValidator;
    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    public List<TrelloBoardDto> fetchTrelloBoards() {
        //List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardDto(filteredBoards);
        // return trelloBoards.stream()
        //  .map(trelloBoardDto -> trelloMapper.mapToBoard(trelloBoardDto))
        //   .collect(Collectors.toList());
    }

    public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createdTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
