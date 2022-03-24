package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

public List<TrelloBoard> mapToBoard(final List<TrelloBoardDto> trelloBoardDto) {
    return trelloBoardDto.stream()
                    .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), maptoList(trelloBoard.getLists())))
                            .collect(Collectors.toList());
           // TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(),maptoList(trelloBoardDto.getLists()));
}
public List<TrelloList> maptoList(final List<TrelloListDto>trelloListDto) {
    return trelloListDto.stream()
            .map(trelloList-> new TrelloList(trelloList.getId(),trelloList.getName(),trelloList.isClosed()))
            .collect(Collectors.toList());
}
public List<TrelloBoardDto> mapToBoardDto(final List<TrelloBoard> trelloBoards) {
    return trelloBoards.stream()
            .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
            .collect(Collectors.toList());
}
public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
    return trelloLists.stream()
            .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(),trelloList.isClosed()))
            .collect(Collectors.toList());
}
public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
    return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(),trelloCard.getPos(), trelloCard.getListId());
}
    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
