package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>>getTrelloBoards() {
       return ResponseEntity.ok(trelloClient.getTrelloBoards());   //.stream()
              //  .filter(trelloClient -> trelloClient.getName().equals("Kodilla-application"))
                //.collect(Collectors.toList());

       // trelloBoards.forEach(trelloBoardDto -> {
              //      System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
           // System.out.println("This board contains lists: ");
           // trelloBoardDto.getLists().forEach(trelloList -> {
              //  System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
          //  });
       // });
    }
    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard>  createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));
    }
}

